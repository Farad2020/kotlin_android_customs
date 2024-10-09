package kz.farad2020.domain.repo

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.domain.model.NetworkResult
import okio.IOException
import okio.buffer
import okio.sink
import java.io.File
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val apiService: GitHubApiService,
    private val context: Context
): MainRepository {

    override suspend fun searchRepositories(query: String)
    : Flow<NetworkResult<List<GitHubRepository>>> = flow {
        emit(NetworkResult.Loading())

        try {
            val response = apiService.searchRepositories(query)
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkResult.Success(response.body()!!.repositories))
            } else {
                emit(NetworkResult.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error("Exception: ${e.message}"))
        }
    }

    override suspend fun downloadRepository(repoData: GitHubRepository): File? {
        return try {
            // Call repository to download the file using the provided URL
            val responseBody = apiService.downloadRepository(repoData.getDownloadLink())


            // Get the external file path
            val zipFile = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                "${repoData.getLogin()}:${repoData.name}.zip")

            // Write the response body to file
            responseBody.source().use { source ->
                zipFile.sink().buffer().use { sink ->
                    sink.writeAll(source)
                }
            }

            zipFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}