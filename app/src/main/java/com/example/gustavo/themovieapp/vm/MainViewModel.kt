package com.example.gustavo.themovieapp.vm

import android.arch.lifecycle.MutableLiveData
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.controller.MainController

/**
 * Created by Gustavo on 16/09/17.
 */
class MainViewModel : BasicViewModel(){

    public val movieList : MutableLiveData<List<Movie>> = MutableLiveData();

    val controller : MainController = MainController();

    fun loadMovies(offset : Int){
        controller.getListDefault(movieList);
    }

}