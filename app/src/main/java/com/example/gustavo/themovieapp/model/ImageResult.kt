package com.example.gustavo.themovieapp.model

/**
 * Created by Gustavo on 20/09/17.
 */
data class ImageResult(val id : Int,
                       val backdrops : List<Image>,
                       val posters : List<Image>) {
}