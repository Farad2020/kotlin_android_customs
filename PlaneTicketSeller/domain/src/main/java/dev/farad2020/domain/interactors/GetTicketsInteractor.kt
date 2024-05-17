package dev.farad2020.domain.interactors

import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.repositories.PlaneTicketRepository
import dev.farad2020.domain.usecases.GetOffersUseCase
import dev.farad2020.domain.usecases.GetTicketOffersUseCase
import dev.farad2020.domain.usecases.GetTicketsUseCase
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetTicketsInteractor(
    private val repo: PlaneTicketRepository
): GetTicketsUseCase {
    override suspend fun execute() = repo.getTickets()
}