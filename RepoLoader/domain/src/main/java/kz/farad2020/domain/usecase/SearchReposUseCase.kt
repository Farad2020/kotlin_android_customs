package kz.farad2020.domain.usecase

import kz.farad2020.domain.repo.MainRepository
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val repo: MainRepository
) {
    suspend operator fun invoke(searchStr: String) = repo.searchRepositories(searchStr)
}