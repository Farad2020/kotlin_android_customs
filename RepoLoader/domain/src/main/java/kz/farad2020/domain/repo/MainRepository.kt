package kz.farad2020.domain.repo

import kz.farad2020.domain.api.GitHubService
import kz.farad2020.domain.model.GitHubRepository

class MainRepository(private val apiService: GitHubService) {

    suspend fun searchRepositories(query: String): List<GitHubRepository> {
        val response = apiService.searchRepositories(query)
        return response.repositories
    }
}