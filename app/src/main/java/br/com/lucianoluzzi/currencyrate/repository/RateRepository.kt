package br.com.lucianoluzzi.currencyrate.repository

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RateRepository {
    companion object {
        private const val RATE_SERVICE_BASE_URL = "https://api.exchangeratesapi.io"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(RATE_SERVICE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val rateService by lazy {
        retrofit.create(RateService::class.java)
    }

    fun getRates(): Single<RatesResponse> {
        return rateService.rates()
    }
}