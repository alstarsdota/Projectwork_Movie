package com.example.project_work
import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results") val movies: List<Movie>
    )