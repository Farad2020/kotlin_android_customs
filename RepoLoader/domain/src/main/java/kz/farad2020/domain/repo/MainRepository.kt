package kz.farad2020.domain.repo

import kz.farad2020.domain.model.GitHubRepository
import okhttp3.ResponseBody

interface MainRepository {
    suspend fun searchRepositories(query: String): List<GitHubRepository>

    suspend fun downloadRepository(url: String): ResponseBody
}