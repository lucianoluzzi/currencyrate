package br.com.lucianoluzzi.currencyrate.repository

import com.google.gson.annotations.SerializedName
import br.com.lucianoluzzi.currencyrate.model.RateModel

class RatesResponse(val base: String, val date: String, @SerializedName("rates") val rateModel: RateModel)