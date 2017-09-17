package com.example.gustavo.themovieapp.ui.adapter.vh

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.gustavo.themovieapp.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, listener: (Movie) -> Unit) = with(itemView) {
        iv_image.loadUrl(movie.backdrop_path)
        tv_details.setOnClickListener{ listener(movie) }
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}