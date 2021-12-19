package com.shahry.com.eg

import android.app.Application
import com.shahry.com.eg.di.repositoryModule
import com.shahry.com.eg.di.serviceApiModule
import com.shahry.com.eg.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    serviceApiModule
                )
            )
        }
    }

}