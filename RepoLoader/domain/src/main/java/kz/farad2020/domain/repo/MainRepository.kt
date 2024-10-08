package kz.farad2020.domain.repo

import kz.farad2020.domain.model.GitHubRepository
import java.io.File

interface MainRepository {
    suspend fun searchRepositories(query: String): List<GitHubRepository>

    suspend fun downloadRepository(repoData: GitHubRepository): File?
}