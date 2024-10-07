package kz.farad2020.repoloader

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RepoLoaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize libraries, services, etc.


        setupLogger()
    }


    private fun setupLogger(){
        Timber.plant(Timber.DebugTree())
    }
}