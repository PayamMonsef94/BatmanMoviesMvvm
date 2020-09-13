package com.example.batmanmoviesmvvm.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
        @SerializedName("Response")
        val response: Boolean,
        @SerializedName("Search")
        val movies: List<Movie>
)

