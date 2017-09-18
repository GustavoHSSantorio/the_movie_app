package com.example.gustavo.themovieapp.ui.adapter.vh

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.util.ConfigurationUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, listener: (Movie) -> Unit) = with(itemView) {
        tv_tilte.text = movie.title
        tv_description.text = movie.overview
        iv_image.loadUrl(movie.backdrop_path)
        tv_details.setOnClickListener{ listener(movie) }
    }

    fun ImageView.loadUrl(url: String?) {
        Picasso.with(context).load(ConfigurationUtils.secure_base_url + ConfigurationUtils.backdrop_sizes + url).placeholder(android.R.drawable.ic_menu_upload).into(this)
    }
}