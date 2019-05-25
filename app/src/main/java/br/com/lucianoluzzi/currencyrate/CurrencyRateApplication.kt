package br.com.lucianoluzzi.currencyrate

import android.app.Application
import br.com.lucianoluzzi.currencyrate.model.RatesViewModel
import br.com.lucianoluzzi.currencyrate.repository.RateRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyRateApplication : Application() {
    private val currencyRateModule = module {
        viewModel {
            RatesViewModel(get())
        }

        single {
            RateRepository()
        }

        single {
            buildRetrofit()
        }
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RateRepository.RATE_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(currencyRateModule)
        }
    }
}