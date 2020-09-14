package com.example.batmanmoviesmvvm.data.api

import com.example.batmanmoviesmvvm.data.MovieListResponse
import com.example.batmanmoviesmvvm.data.model.Detail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getMovieList(
        @Query("s") movieName: String,
        @Query("apikey") apiKey: String
    ): Single<MovieListResponse>

    @GET(".")
    fun getMovieDetail(
        @Query("i") movieId: String,
        @Query("apikey") apiKey: String
    ): Single<Detail>
}