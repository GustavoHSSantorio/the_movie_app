package com.example.gustavo.themovieapp.controller

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.api.RetrofitAPI
import com.example.gustavo.themovieapp.model.Configuration
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Gustavo on 16/09/17.
 */
class MainController : BasicController(){

    val retrofitApi = RetrofitAPI.create();

    fun getListNowPlayng(movieLiveData: MutableLiveData<List<Movie>>){
        retrofitApi.searchNowPlayng("dadbc9342b99903fed37ffc73e2833bf", "en-US", "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                })
    }

    fun getListPopular(movieLiveData: MutableLiveData<List<Movie>>){
        retrofitApi.searchPopular("dadbc9342b99903fed37ffc73e2833bf", "en-US", "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            movieLiveData.value = result.movies
                        }
                        , {error ->
                    Log.e("CONTROLLER", error.message)
                })
    }

    fun getListUpcoming(movieLiveData: MutableLiveData<List<Movie>>){
        retrofitApi.searchUpcoming("dadbc9342b99903fed37ffc73e2833bf", "en-US", "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            movieLiveData.value = result.movies
                        }
                        , {error ->
                    Log.e("CONTROLLER", error.message)
                })
    }

    fun getListSearched(movieLiveData: MutableLiveData<List<Movie>>, query : String){
        retrofitApi.searchMovie("dadbc9342b99903fed37ffc73e2833bf", "en-US", query, "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.e("CONTROLLER", result.movies.size.toString())
                            movieLiveData.value = result.movies
                        }
                        , {error ->
                            Log.e("CONTROLLER", error.message)
                })
    }


    fun getConfiguration(movieLiveData: MutableLiveData<Configuration>){
        retrofitApi.getConfigurations("dadbc9342b99903fed37ffc73e2833bf")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            movieLiveData.value = result
                        }
                        , {error ->
                    Log.e("CONTROLLER", error.message)
                })
    }
}
