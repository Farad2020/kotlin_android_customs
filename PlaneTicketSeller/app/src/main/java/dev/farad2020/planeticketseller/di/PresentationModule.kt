package dev.farad2020.planeticketseller.di

import dev.farad2020.planeticketseller.ui.destination.DestinationSelectedViewModel
import dev.farad2020.planeticketseller.ui.ticketList.TicketListViewModel
import dev.farad2020.planeticketseller.ui.ticketMain.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { TicketsViewModel(get()) }


    viewModel { DestinationSelectedViewModel(get()) }


    viewModel { TicketListViewModel(get()) }
}