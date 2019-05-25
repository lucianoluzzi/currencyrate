package br.com.lucianoluzzi.currencyrate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.lucianoluzzi.currencyrate.R
import br.com.lucianoluzzi.currencyrate.databinding.ActivityMainBinding
import br.com.lucianoluzzi.currencyrate.model.RatesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val ratesViewModel: RatesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
