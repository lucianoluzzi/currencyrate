package br.com.lucianoluzzi.currencyrate.repository

import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Retrofit

class RateRepository : KoinComponent {
    companion object {
        const val RATE_SERVICE_BASE_URL = "https://api.exchangeratesapi.io"
    }

    private val retrofit: Retrofit by inject()
    private val rateService by lazy {
        retrofit.create(RateService::class.java)
    }

    fun getRates(): Single<RatesResponse> {
        return rateService.rates()
    }
}