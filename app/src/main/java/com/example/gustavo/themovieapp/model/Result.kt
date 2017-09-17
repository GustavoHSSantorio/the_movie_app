package com.example.gustavo.themovieapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Gustavo on 16/09/17.
 */
data class Result(@SerializedName("results") val movies : List<Movie>)