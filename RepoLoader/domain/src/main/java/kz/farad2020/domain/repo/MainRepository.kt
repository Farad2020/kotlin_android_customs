package kz.farad2020.domain.repo

import kotlinx.coroutines.flow.Flow
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.domain.model.NetworkResult
import java.io.File

interface MainRepository {
    suspend fun searchRepositories(query: String): Flow<NetworkResult<List<GitHubRepository>>>

    suspend fun downloadRepository(repoData: GitHubRepository): File?
}