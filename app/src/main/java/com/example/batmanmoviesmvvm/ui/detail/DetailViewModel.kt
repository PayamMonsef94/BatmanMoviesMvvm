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

    private val _emptyDetail = MutableLiveData<Boolean>()
    val emptyDetail: LiveData<Boolean> = _emptyDetail

    fun getMovieDetail(movieId: String) {
        this.movieId = movieId
        disposable.add(
            repository.getMovieDetail(movieId).with()
                .subscribe({ t: Detail ->
                    _emptyDetail.value = false
                    detail.value = t
                }, { t: Throwable? ->
                    _emptyDetail.value = true
                })
        )
    }


}