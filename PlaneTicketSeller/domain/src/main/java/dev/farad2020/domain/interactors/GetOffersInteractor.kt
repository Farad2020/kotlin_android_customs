package dev.farad2020.domain.interactors

import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.repositories.PlaneTicketRepository
import dev.farad2020.domain.usecases.GetOffersUseCase
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetOffersInteractor(
    private val repo: PlaneTicketRepository
): GetOffersUseCase {
    override suspend fun execute() = repo.getOffers()
}