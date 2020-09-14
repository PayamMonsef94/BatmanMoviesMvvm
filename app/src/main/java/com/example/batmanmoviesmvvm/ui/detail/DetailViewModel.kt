package com.example.batmanmoviesmvvm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.batmanmoviesmvvm.base.BaseViewModel
import com.example.batmanmoviesmvvm.data.DataRepository
import com.example.batmanmoviesmvvm.data.model.Detail
import com.example.batmanmoviesmvvm.extension.with
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: DataRepository) :
    BaseViewModel() {

    val detail = MutableLiveData<Detail>()
    private lateinit var movieId: String

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _emptyDetail = MutableLiveData<Boolean>()
    val emptyDetail: LiveData<Boolean> = _emptyDetail

    fun getMovieDetail(movieId: String) {
        this.movieId = movieId
        disposable.add(
            repository.getMovieDetail(movieId).with()
                .doAfterTerminate { _dataLoading.value = false }
                .subscribe({ t: Detail ->
                    maxTry = 2
                    _emptyDetail.value = false
                    detail.value = t
                }, { t: Throwable? ->
                    if (maxTry > 0) {
                        maxTry -= 1
                        getMovieDetail(movieId)
                    } else {
                        _emptyDetail.value = true
                    }
                })
        )
    }

    fun refresh() {
        _dataLoading.value = true
        getMovieDetail(movieId)
    }

}