package kz.farad2020.domain.usecase

import javax.inject.Inject

data class BaseUseCasesPack @Inject constructor(
    val searchRepository: SearchReposUseCase,
    val downloadRepository: DownloadRepoUseCase,
)