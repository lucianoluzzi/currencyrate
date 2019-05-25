package br.com.lucianoluzzi.currencyrate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.lucianoluzzi.currencyrate.R
import br.com.lucianoluzzi.currencyrate.databinding.ActivityMainBinding
import model.RatesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var ratesViewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratesViewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)

        val mainActivityBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainActivityBinding.lifecycleOwner = this
        mainActivityBinding.ratesViewModel = ratesViewModel
    }

    override fun onResume() {
        super.onResume()
        ratesViewModel.getRepositories()
    }
}
