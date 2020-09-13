package com.example.batmanmoviesmvvm.data.api

import com.example.batmanmoviesmvvm.data.model.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    fun getMovieList(
        @Query("s") movieName: String,
        @Query("apikey") apiKey: String
    ): Single<MovieListResponse>
}