package com.example.gustavo.themovieapp.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Configuration
import com.example.gustavo.themovieapp.util.ConfigurationUtils
import com.example.gustavo.themovieapp.vm.MainViewModel

class SplashActivity : BasicActivity(MainViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        observeError()
        observeConfiguration();
        (getViewModel() as MainViewModel).getConfiguration()
    }

    fun observeConfiguration(){
        (getViewModel() as MainViewModel).configuration.observe(this, Observer<Configuration>{ configuration ->
            ConfigurationUtils.base_url = configuration?.images?.base_url
            ConfigurationUtils.secure_base_url = configuration?.images?.secure_base_url
            ConfigurationUtils.backdrop_sizes = configuration?.images!!.backdrop_sizes[2]
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }
}
