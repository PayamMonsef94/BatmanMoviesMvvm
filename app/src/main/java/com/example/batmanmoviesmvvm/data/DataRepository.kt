package com.example.batmanmoviesmvvm.data

import com.example.batmanmoviesmvvm.data.api.ApiService
import com.example.batmanmoviesmvvm.data.db.AppDao
import com.example.batmanmoviesmvvm.data.model.Detail
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.utils.Constants.Companion.API_KEY
import com.example.batmanmoviesmvvm.utils.Constants.Companion.SEARCH_KEY
import com.example.batmanmoviesmvvm.utils.Network
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val appDao: AppDao,
    private val apiService: ApiService
) {
    @Inject
    lateinit var network: Network

    fun getMovieList(): Single<List<Movie>> {
        val hasConnection = network.isInternetAvailable()
        var observableFromApi: Single<List<Movie>>? = null
        if (hasConnection) {
            observableFromApi = getMovieListFromApi()
        }
        val observableFromDb = getMovieListFromDb()

        return if (hasConnection)
            Single.concatArray(observableFromApi, observableFromDb).firstOrError()
        else
            observableFromDb
    }

    private fun getMovieListFromApi(): Single<List<Movie>> {
        return apiService.getMovieList(SEARCH_KEY, API_KEY).map {
            it.movies
        }.doOnSuccess {
            appDao.insertAllMovies(it)
        }
    }

    private fun getMovieListFromDb() = appDao.getAllMovies()

    fun getMovieDetail(movieId:String) :Single<Detail>{
        val hasConnection = network.isInternetAvailable()
        var observableFromApi: Single<Detail>? = null
        if (hasConnection) {
            observableFromApi = getDetailFromApi(movieId)
        }
        val observableFromDb = getDetailFromDb(movieId)

        return if (hasConnection)
            Single.concatArray(observableFromApi, observableFromDb).firstOrError()
        else
            observableFromDb
    }

    private fun getDetailFromApi(movieId:String): Single<Detail> {
        return apiService.getMovieDetail(movieId, API_KEY).doOnSuccess {
            appDao.insertDetail(it)
        }
    }

    private fun getDetailFromDb(movieId:String) = appDao.getDetail(movieId)

}