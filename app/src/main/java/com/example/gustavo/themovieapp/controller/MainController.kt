package com.example.gustavo.themovieapp.controller

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.api.RetrofitAPI
import com.example.gustavo.themovieapp.model.Configuration
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.example.gustavo.themovieapp.model.Error


/**
 * Created by Gustavo on 16/09/17.
 */
class MainController(val erro : MutableLiveData<com.example.gustavo.themovieapp.model.Error>) : BasicController(){

    val retrofitApi = RetrofitAPI.create();

        fun getListNowPlayng(movieLiveData: MutableLiveData<List<Movie>>, page : Int){
        retrofitApi.searchNowPlayng("dadbc9342b99903fed37ffc73e2833bf", "en-US", page.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            if(movieLiveData.value != null)
                                movieLiveData.value = movieLiveData.value!!.updateMovieList(result.movies)
                            else
                                movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                            erro.value = Error(error.message)
                })
    }

    fun getListPopular(movieLiveData: MutableLiveData<List<Movie>>, page : Int){
        retrofitApi.searchPopular("dadbc9342b99903fed37ffc73e2833bf", "en-US", page.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            if(movieLiveData.value != null)
                                movieLiveData.value = movieLiveData.value!!.updateMovieList(result.movies)
                            else
                                movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                            erro.value = Error(error.message)
                })
    }

    fun getListUpcoming(movieLiveData: MutableLiveData<List<Movie>>){
        retrofitApi.searchUpcoming("dadbc9342b99903fed37ffc73e2833bf", "en-US", "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            if(movieLiveData.value != null)
                                movieLiveData.value = movieLiveData.value!!.updateMovieList(result.movies)
                            else
                                movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                            erro.value = Error(error.message)
                })
    }

    fun getListSearched(movieLiveData: MutableLiveData<List<Movie>>, query : String){
        retrofitApi.searchMovie("dadbc9342b99903fed37ffc73e2833bf", "en-US", query, "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            if(movieLiveData.value != null)
                                movieLiveData.value = movieLiveData.value!!.updateMovieList(result.movies)
                            else
                                movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                            erro.value = Error(error.message)
                })
    }


    fun getConfiguration(configurationsLiveData: MutableLiveData<Configuration>){
        retrofitApi.getConfigurations("dadbc9342b99903fed37ffc73e2833bf")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            configurationsLiveData.value = result
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                            erro.value = Error(error.message)
                })
    }

    fun List<Movie>.updateMovieList(newList : List<Movie>) : List<Movie>{
        val mutable : MutableList<Movie> = this as MutableList<Movie>
        mutable.addAll(newList)
        return mutable
    }
}
