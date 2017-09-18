package com.example.gustavo.themovieapp.ui.activity

import android.app.Fragment
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.ui.fragment.MovieListFragment
import com.example.gustavo.themovieapp.util.Constants
import com.example.gustavo.themovieapp.vm.MainViewModel

/**
 * Created by Gustavo on 16/09/17.
 */

open class BasicActivity(val modelClass: Class<out ViewModel>) : AppCompatActivity() {

    val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    fun getViewModel() : ViewModel = ViewModelProviders.of(this).get(modelClass)

}
