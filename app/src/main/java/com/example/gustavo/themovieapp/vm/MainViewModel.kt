package com.example.gustavo.themovieapp.vm

import android.arch.lifecycle.MutableLiveData
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.controller.MainController
import com.example.gustavo.themovieapp.model.Configuration
import com.example.gustavo.themovieapp.model.Error

/**
 * Created by Gustavo on 16/09/17.
 */
class MainViewModel : BasicViewModel(){

    val movieList : MutableLiveData<List<Movie>> = MutableLiveData();
    val configuration : MutableLiveData<Configuration> = MutableLiveData();

    val controller : MainController = MainController(error);

    fun loadMoviesNowPlayng(){
        controller.getListNowPlayng(movieList);
    }

    fun loadMoviesPopular(){
        controller.getListPopular(movieList);
    }

    fun loadMoviesUpcoming(){
        controller.getListUpcoming(movieList);
    }

    fun searchForMovies(query : String){
        controller.getListSearched(movieList, query);
    }

    fun getConfiguration(){
        controller.getConfiguration(configuration);
    }

}