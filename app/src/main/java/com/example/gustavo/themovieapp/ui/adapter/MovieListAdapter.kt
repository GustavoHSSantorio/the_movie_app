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
import android.support.v4.content.ContextCompat.startActivity



/**
 * Created by Gustavo on 16/09/17.
 */
class MovieListAdapter(var movies : List<Movie>, val context : Context) : RecyclerView.Adapter<MovieViewHolder>() {

    fun updateDataSet(newList : List<Movie>){
        movies = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movies[position], object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(Constants.MovieDetailsConstants.MOVIE_EXTRA, movies[position])
                context.startActivity(intent)
            }
        }, object : View.OnClickListener {
            override fun onClick(v: View?) {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, movies[position].title)
                context.startActivity(Intent.createChooser(share, "Onde quer compartilhar?"))
            }
        })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(parent.inflate())

    fun ViewGroup.inflate(): View  = LayoutInflater.from(context).inflate(R.layout.movie_item, this, false)
}