package com.example.gustavo.themovieapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.util.ConfigurationUtils
import com.example.gustavo.themovieapp.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        inflateViewDetail(intent.extras.get(Constants.MovieDetailsConstants.MOVIE_EXTRA) as Movie)
    }

    fun inflateViewDetail(movie : Movie){
        tv_tilte.text = movie.title
        tv_description.text = movie.overview
        iv_image.loadUrl(movie.backdrop_path)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    fun ImageView.loadUrl(url: String?) {
        Picasso.with(context).load(ConfigurationUtils.secure_base_url + ConfigurationUtils.backdrop_sizes + url).placeholder(android.R.drawable.ic_menu_upload).into(this)
    }
}
