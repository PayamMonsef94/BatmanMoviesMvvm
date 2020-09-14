package com.example.batmanmoviesmvvm.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()

    var maxTry = 2

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.clear()
            disposable.dispose()
        }
    }

}