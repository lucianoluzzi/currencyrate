package br.com.lucianoluzzi.currencyrate.repository

import io.reactivex.Single
import retrofit2.http.GET

interface RateService {
    @GET("latest")
    fun rates(): Single<RatesResponse>
}