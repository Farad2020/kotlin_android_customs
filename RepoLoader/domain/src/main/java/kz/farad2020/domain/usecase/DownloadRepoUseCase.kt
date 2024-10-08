package kz.farad2020.domain.usecase

import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.domain.repo.MainRepository
import javax.inject.Inject

class DownloadRepoUseCase @Inject constructor(
    private val repo: MainRepository
) {
    suspend operator fun invoke(repoData: GitHubRepository) = repo.downloadRepository(repoData)
}