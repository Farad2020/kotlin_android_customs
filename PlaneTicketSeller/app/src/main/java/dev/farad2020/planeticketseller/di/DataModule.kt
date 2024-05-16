package dev.farad2020.planeticketseller.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import dev.farad2020.data.repositories.PlaneTicketRepoImpl
import dev.farad2020.domain.repositories.PlaneTicketRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SHARED_PREFS = "tickets_shared_prefs"

val dataModule = module {

    single<SharedPreferences> { androidContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE) }

    single<ConnectivityManager> { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager }
}