package br.com.lucianoluzzi.currencyrate

import android.app.Application
import br.com.lucianoluzzi.currencyrate.repository.RateRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class CurrencyRateApplication : Application() {
    private val currencyRateModule = module {
        single {
            RateRepository()
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(currencyRateModule))
    }
}