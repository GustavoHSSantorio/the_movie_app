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

    private var page : Int = 0

    val controller : MainController = MainController(error);

    fun loadMoviesNowPlayng(page : Int){
        controller.getListNowPlayng(movieList, page);
    }

    fun loadMoviesPopular(page : Int){
        controller.getListPopular(movieList, page);
    }

    fun loadMoviesUpcoming(page : Int){
        controller.getListUpcoming(movieList);
    }

    fun searchForMovies(query : String, page : Int){
        controller.getListSearched(movieList, query);
    }

    fun getConfiguration(){
        controller.getConfiguration(configuration);
    }

    fun reinitNextPage() {
        page = 0
        movieList.value = null
    }

    fun getNextPage() : Int = ++page

}