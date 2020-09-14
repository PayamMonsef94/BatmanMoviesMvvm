package com.example.batmanmoviesmvvm.data

import android.util.Log
import com.example.batmanmoviesmvvm.data.api.ApiService
import com.example.batmanmoviesmvvm.data.db.AppDao
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.utils.Constants.Companion.API_KEY
import com.example.batmanmoviesmvvm.utils.Constants.Companion.SEARCH_KEY
import com.example.batmanmoviesmvvm.utils.Network
import io.reactivex.Observable
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
            Log.i("ttt", "getMovieListFromApi size: ${it.size}")
            for (item in it) {
                Log.i("ttt", "getMovieListFromApi: ${item.Title}")
                //cryptocurrenciesDao.insertCryptocurrency(item)
            }
        }
    }

    private fun getMovieListFromDb(): Single<List<Movie>> {
        return appDao.getAllMovies()
            .doOnSuccess {
                for (item in it) {
                    Log.i("ttt", "getMovieListFromDb: ${item.Title}")
                    //cryptocurrenciesDao.insertCryptocurrency(item)
                }
            }
    }

}