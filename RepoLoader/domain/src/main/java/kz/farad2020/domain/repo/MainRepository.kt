package kz.farad2020.domain.repo

import kz.farad2020.domain.model.GitHubRepository

interface MainRepository {
    suspend fun searchRepositories(query: String): List<GitHubRepository>
}