package br.com.lucianoluzzi.currencyrate.model

class RateModel(private val USD: Float, private val PLN: Float) {
    fun showUSD(): String = "USD: $USD"
    fun showPLN(): String = "PLN: $PLN"
}