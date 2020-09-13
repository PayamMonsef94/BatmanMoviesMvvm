package com.example.batmanmoviesmvvm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()
    val toastMessage = MutableLiveData<String>()
    val loadingProgress = MutableLiveData<Boolean>()

    var maxTry = 2

    fun observeLoadingProgress(): LiveData<Boolean> {
        return loadingProgress
    }

    fun observeToastMessage(): LiveData<String> {
        return toastMessage
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.clear()
            disposable.dispose()
        }
    }

}