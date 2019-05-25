package br.com.lucianoluzzi.currencyrate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.lucianoluzzi.currencyrate.R
import br.com.lucianoluzzi.currencyrate.databinding.ActivityMainBinding
import br.com.lucianoluzzi.currencyrate.model.RatesViewModel
import br.com.lucianoluzzi.currencyrate.repository.RateRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val rateRepository: RateRepository by inject()
    private lateinit var ratesViewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratesViewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)
        ratesViewModel.rateRepository = rateRepository

        val mainActivityBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainActivityBinding.lifecycleOwner = this
        mainActivityBinding.ratesViewModel = ratesViewModel
    }

    override fun onResume() {
        super.onResume()
        ratesViewModel.getRates()
    }

    override fun onPause() {
        super.onPause()
        ratesViewModel.stop()
    }
}
