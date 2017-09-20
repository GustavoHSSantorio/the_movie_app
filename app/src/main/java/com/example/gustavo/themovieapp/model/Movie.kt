package com.example.gustavo.themovieapp.model

import java.io.Serializable

/**
 * Created by Gustavo on 16/09/17.
 */
data class Movie(val poster_path : String,
                 val adult: Boolean,
                 val overview : String,
                 val release_date: String,
                 val id : Int,
                 val title : String,
                 val backdrop_path : String,
                 val vote_average : Float) : Serializable

