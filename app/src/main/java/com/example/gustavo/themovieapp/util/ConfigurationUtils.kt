package com.example.gustavo.themovieapp.util

/**
 * Created by Gustavo on 17/09/17.
 */
class ConfigurationUtils {

    companion object Configuration{
        var base_url : String? = null
        var secure_base_url : String? = null
        var backdrop_sizes : String? = null
        var logo_sizes : String? = null

        fun getFullUrl() : String = secure_base_url + backdrop_sizes
    }
}