package br.com.lucianoluzzi.currencyrate.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lucianoluzzi.currencyrate.repository.RateRepository
import br.com.lucianoluzzi.currencyrate.repository.RatesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import model.BaseViewModel
import java.util.concurrent.TimeUnit

class RatesViewModel(private val rateRepository: RateRepository) : BaseViewModel() {
    private val _rates = MutableLiveData<RateModel>().apply {
        RateModel(0f, 0f)
    }
    val rates: LiveData<RateModel> = _rates

    fun getRates() {
        val disposable = Observable.interval(0, 1, TimeUnit.MINUTES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                rateRepository.getRates()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<RatesResponse>() {
                        override fun onSuccess(response: RatesResponse) {
                            Log.d("RATE_REPOSITORY", "call")
                            _rates.value = response.rateModel
                        }

                        override fun onError(e: Throwable) {
                            Log.d("RATE_REPOSITORY", e.message)
                        }
                    })
            }
        addDisposable(disposable)
    }

    fun stop() {
        onCleared()
    }
}