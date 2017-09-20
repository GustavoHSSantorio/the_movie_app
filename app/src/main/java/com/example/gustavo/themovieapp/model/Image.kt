package com.example.gustavo.themovieapp.model

/**
 * Created by Gustavo on 20/09/17.
 */
data class Image(val aspect_ratio : Float,
                 val file_path : String,
                 val heigth : Float,
                 val iso_639_1 : String?,
                 val vote_average : Float,
                 val vote_count : Float,
                 val width : Float) {
}