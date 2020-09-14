package com.example.batmanmoviesmvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class Detail(
    @PrimaryKey
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Country: String,
    val Awards: String,
    val Poster: String,
    val Metascore: String,
    val imdbRating: String,
    val imdbVotes: String
)