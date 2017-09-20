package com.example.gustavo.themovieapp.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Configuration
import com.example.gustavo.themovieapp.util.ConfigurationUtils
import com.example.gustavo.themovieapp.vm.MainViewModel
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BasicActivity(MainViewModel::class.java) {

    val handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iv_image.setBackgroundResource(R.drawable.splash_animation)
        val frameAnimation = iv_image.getBackground() as AnimationDrawable
        frameAnimation.start()

        observeError()
        observeConfiguration();

        handler.postDelayed({
            (getViewModel() as MainViewModel).getConfiguration()
        }, 4000)
    }

    fun observeConfiguration(){
        (getViewModel() as MainViewModel).configuration.observe(this, Observer<Configuration>{ configuration ->
            ConfigurationUtils.base_url = configuration?.images?.base_url
            ConfigurationUtils.secure_base_url = configuration?.images?.secure_base_url
            ConfigurationUtils.backdrop_sizes = configuration?.images!!.backdrop_sizes[2]
            ConfigurationUtils.logo_sizes = configuration?.images!!.logo_sizes[5]
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }
}
