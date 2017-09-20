package com.example.gustavo.themovieapp.ui.activity

import android.arch.lifecycle.Observer
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.util.ConfigurationUtils
import com.example.gustavo.themovieapp.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.nav_header_main.*
import android.view.animation.AnimationUtils
import com.example.gustavo.themovieapp.model.Image
import com.example.gustavo.themovieapp.model.ImageResult
import com.example.gustavo.themovieapp.vm.MainViewModel
import android.util.DisplayMetrics
import android.view.animation.Animation
import android.widget.*
import com.squareup.picasso.Target
import retrofit2.http.Url
import java.net.URL


class MovieDetailActivity : BasicActivity(MainViewModel::class.java) {

    var imageList : List<Image>? = null
    var index : Int = 1

    val handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        observeImages()

        inflateViewDetail(intent.extras.get(Constants.MovieDetailsConstants.MOVIE_EXTRA) as Movie)
    }

    fun inflateViewDetail(movie : Movie){
        (getViewModel() as MainViewModel).getMovieImages(movie.id)

        tv_tilte.text = movie.title
        tv_voute.text = movie.vote_average.toString()
        tv_description.text = movie.overview
        is_banner.setFactory(ViewSwitcher.ViewFactory {
            val imageView = ImageView(applicationContext)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.loadUrl(ConfigurationUtils.getFullUrl() + movie.backdrop_path)
            return@ViewFactory imageView
        })

        val animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        val animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)
        animationIn.duration = 1500
        animationOut.duration = 1500

        is_banner.setInAnimation(animationIn)
        is_banner.setOutAnimation(animationOut)
    }

    fun observeImages(){
        (getViewModel() as MainViewModel).movieImages.observe(this, Observer<ImageResult> {  imageResult ->
            imageList = imageResult!!.backdrops
            updateImage()
        })
    }

    fun updateImage(){
        if (imageList != null) {
            if (index >= imageList!!.size)
                index = 1
            is_banner.loadImage(ConfigurationUtils.getFullUrl() + imageList!![index].file_path)
            index++
        }
//        iv_image.minimumHeight = (getResources().getDisplayMetrics().widthPixels.toFloat() * imageList!![index].aspect_ratio).toInt()
        callPost()
    }

    fun callPost(){
        handler.postDelayed({
            updateImage()
        }, 3000)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }

    fun ImageSwitcher.loadImage(url: String?){
        Picasso.with(context)
                .load(url)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        this@loadImage.setImageDrawable(BitmapDrawable(getResources(), bitmap))
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }
                });
    }

    fun ImageView.loadUrl(url: String?) {
        Picasso.with(context).load(url).placeholder(android.R.drawable.ic_menu_upload).into(this)
    }
}
