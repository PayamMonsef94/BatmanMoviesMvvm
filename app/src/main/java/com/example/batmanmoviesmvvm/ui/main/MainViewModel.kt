package com.example.batmanmoviesmvvm.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.batmanmoviesmvvm.base.BaseViewModel
import com.example.batmanmoviesmvvm.data.DataRepository
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.extension.with
import com.example.batmanmoviesmvvm.utils.Event
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: DataRepository) : BaseViewModel() {

    val movieList = MutableLiveData<List<Movie>>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _openDetailEvent = MutableLiveData<Event<String>>()
    val openDetailEvent: LiveData<Event<String>> = _openDetailEvent

    init {
        getMovieList()
    }

    private fun getMovieList() {
        disposable.add(
            repository.getMovieList().with()
                .doAfterTerminate { _dataLoading.value = false }
                .subscribe({ t: List<Movie> ->
                    maxTry = 2
                    movieList.value = t
                }, { t: Throwable? ->
                    if (maxTry > 0) {
                        maxTry -= 1
                        getMovieList()
                    } else {
                        toastMessage.value = "خطا در برقراری ارتباط با اینترنت!"
                    }
                })
        )
    }

    fun refresh() {
        _dataLoading.value = true
        getMovieList()
    }

    fun openDetail(movieId: String) {
        _openDetailEvent.value = Event(movieId)
    }
}