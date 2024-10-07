package kz.farad2020.domain.repo

import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.GitHubRepository
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val apiService: GitHubApiService
): MainRepository {

    override suspend fun searchRepositories(query: String): List<GitHubRepository> {
        val response = apiService.searchRepositories(query)
        return response.repositories
    }
}