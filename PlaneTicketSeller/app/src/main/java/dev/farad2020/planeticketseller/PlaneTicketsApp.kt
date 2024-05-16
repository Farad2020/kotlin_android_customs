package dev.farad2020.planeticketseller

import android.app.Application
import dev.farad2020.data.di.networkModule
import dev.farad2020.planeticketseller.di.dataModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class PlaneTicketsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDi()

        setupLogger()
    }


    private fun setupDi(){
        startKoin {
            androidContext(this@PlaneTicketsApp)
            modules(
                dataModule,
                networkModule
            )
        }

    }

    private fun setupLogger(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}