package kz.farad2020.repoloader

import android.app.Application
import timber.log.Timber

class RepoLoaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize libraries, services, etc.

        Timber.plant(Timber.DebugTree())
    }
}