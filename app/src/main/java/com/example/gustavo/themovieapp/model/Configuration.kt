package com.example.gustavo.themovieapp.model

/**
 * Created by Gustavo on 17/09/17.
 */
data class Configuration(val images : Images)
data class Images(val base_url: String, val secure_base_url : String, val backdrop_sizes : List<String>, val logo_sizes : List<String>)