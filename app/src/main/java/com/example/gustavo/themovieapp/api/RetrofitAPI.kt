package com.example.gustavo.themovieapp.api

import com.example.gustavo.themovieapp.model.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Gustavo on 16/09/17.
 */
interface RetrofitAPI {

    @GET("movie/now_playing")
    fun searchNowPlayng(@Query("api_key") key: String, @Query("language") language : String, @Query("page") page : String): Observable<Result>;

    @GET("movie/popular")
    fun searchPopular(@Query("api_key") key: String, @Query("language") language : String, @Query("page") page : String): Observable<Result>;


    companion object Factory{
        fun create(): RetrofitAPI {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()

            return retrofit.create(RetrofitAPI::class.java);
        }
    }

}