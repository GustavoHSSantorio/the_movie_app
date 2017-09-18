package com.example.gustavo.themovieapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.ui.activity.MovieDetailActivity
import com.example.gustavo.themovieapp.ui.adapter.vh.MovieViewHolder
import com.example.gustavo.themovieapp.util.Constants

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieListAdapter(val movies : List<Movie>, val context : Context) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int = Integer.MAX_VALUE

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movies[position % movies.size]){
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MovieDetailsConstants.MOVIE_EXTRA, movies[position])
        context.startActivity(intent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(parent.inflate())

    fun ViewGroup.inflate(): View  = LayoutInflater.from(context).inflate(R.layout.movie_item, this, false)
}