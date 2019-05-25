package model

class RateModel(private val USD: Float, private val PLN: Float) {
    fun showUSD(): String = USD.toString()
    fun showPLN(): String = PLN.toString()
}