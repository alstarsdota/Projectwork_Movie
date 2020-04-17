package com.example.project_work

import com.google.gson.annotations.SerializedName

class Movie (
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
//    @SerializedName("title") val title: String,
//    @SerializedName("genre") val genre: String,
//    @SerializedName("rating") val rating: Double,
//    @SerializedName("description") val description: String,
//    @SerializedName("mainPoster") val mainPoster: Int,
)
