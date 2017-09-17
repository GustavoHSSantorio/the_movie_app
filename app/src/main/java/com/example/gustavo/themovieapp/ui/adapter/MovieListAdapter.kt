package com.example.gustavo.themovieapp.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.ui.adapter.vh.MovieViewHolder

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieListAdapter(val movies : List<Movie>, val listenter: (Movie) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movies[position],listenter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(parent.inflate())

    fun ViewGroup.inflate(): View  = LayoutInflater.from(context).inflate(R.layout.movie_item, this, false)
}