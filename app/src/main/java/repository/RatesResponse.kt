package repository

import com.google.gson.annotations.SerializedName
import model.RateModel

class RatesResponse(val base: String, val date: String, @SerializedName("rates") val rateModel: RateModel)