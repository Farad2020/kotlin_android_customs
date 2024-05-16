package dev.farad2020.data.di

import dev.farad2020.data.repositories.PlaneTicketRepoImpl
import dev.farad2020.domain.repositories.PlaneTicketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PlaneTicketRepository> { PlaneTicketRepoImpl(get()) }
}