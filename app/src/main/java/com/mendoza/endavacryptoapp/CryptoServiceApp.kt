package com.mendoza.endavacryptoapp

import android.app.Application
import com.mendoza.endavacryptoapp.network.networkModule
import com.mendoza.endavacryptoapp.ui.market.module.marketModule
import org.koin.core.context.startKoin

class CryptoServiceApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                networkModule,
                marketModule
            )
        }
    }
}