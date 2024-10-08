package kz.farad2020.domain.repo

import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.GitHubRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val apiService: GitHubApiService
): MainRepository {

    override suspend fun searchRepositories(query: String): List<GitHubRepository> {
        val response = apiService.searchRepositories(query)
        return response.repositories
    }

    override suspend fun downloadRepository(url: String): ResponseBody {
        val response = apiService.downloadRepository(url)
        return response
    }
}