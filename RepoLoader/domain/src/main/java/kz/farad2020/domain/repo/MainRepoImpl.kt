package kz.farad2020.domain.repo

import android.content.Context
import android.os.Environment
import dagger.hilt.android.qualifiers.ApplicationContext
import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.GitHubRepository
import okio.IOException
import okio.buffer
import okio.sink
import java.io.File
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val apiService: GitHubApiService,
    private val context: Context
): MainRepository {

    override suspend fun searchRepositories(query: String): List<GitHubRepository> {
        val response = apiService.searchRepositories(query)
        return response.repositories
    }

    override suspend fun downloadRepository(repoData: GitHubRepository): File? {
        return try {
            // Call repository to download the file using the provided URL
            val responseBody = apiService.downloadRepository(repoData.getDownloadLink())


            // Get the external file path
            val zipFile = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                "${repoData.name}.zip")

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