package kz.farad2020.domain.usecase

import android.content.Context
import android.os.Environment
import kz.farad2020.domain.repo.MainRepository
import okio.IOException
import okio.buffer
import okio.sink
import java.io.File
import javax.inject.Inject

class DownloadRepoUseCase @Inject constructor(
    private val repo: MainRepository
) {
//    TODO refactor usage of context
    suspend operator fun invoke(url: String, context: Context): File?{
       return try {
            // Call repository to download the file using the provided URL
            val responseBody = repo.downloadRepository(url)

            // Get the external file path
            val zipFile = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                "realworld.zip")

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