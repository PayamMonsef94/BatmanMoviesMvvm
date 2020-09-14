package com.example.batmanmoviesmvvm.data

import com.example.batmanmoviesmvvm.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
        @SerializedName("Response")
        val response: Boolean,
        @SerializedName("Search")
        val movies: List<Movie>
)

