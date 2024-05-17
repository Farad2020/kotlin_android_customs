package dev.farad2020.planeticketseller.di

import dev.farad2020.domain.interactors.GetOffersInteractor
import dev.farad2020.domain.interactors.GetTicketOffersInteractor
import dev.farad2020.domain.interactors.GetTicketsInteractor
import dev.farad2020.domain.usecases.GetOffersUseCase
import dev.farad2020.domain.usecases.GetTicketOffersUseCase
import dev.farad2020.domain.usecases.GetTicketsUseCase
import org.koin.dsl.module

val domainModule = module{
    factory<GetOffersUseCase> { GetOffersInteractor(get()) }
    factory<GetTicketsUseCase> { GetTicketsInteractor(get()) }
    factory<GetTicketOffersUseCase> { GetTicketOffersInteractor(get()) }
}