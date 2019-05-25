package br.com.lucianoluzzi.currencyrate.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import br.com.lucianoluzzi.currencyrate.repository.RateRepository
import br.com.lucianoluzzi.currencyrate.repository.RatesResponse

class RatesViewModel : BaseViewModel() {
    private val rateRepository = RateRepository()
    private val _rates = MutableLiveData<RateModel>().apply {
        RateModel(0f, 0f)
    }
    val rates: LiveData<RateModel> = _rates

    fun getRepositories() {
        val disposable = rateRepository.getRates().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RatesResponse>() {
                override fun onSuccess(response: RatesResponse) {
                    _rates.value = response.rateModel
                }

                override fun onError(e: Throwable) {
                    Log.d("RATE_REPOSITORY", e.stackTrace.toString())
                }
            })
        addDisposable(disposable)
    }
}