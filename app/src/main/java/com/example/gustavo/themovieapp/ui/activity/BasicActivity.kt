package com.example.gustavo.themovieapp.ui.activity

import android.support.v7.app.AlertDialog
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.example.gustavo.themovieapp.model.Error
import com.example.gustavo.themovieapp.vm.BasicViewModel
import com.example.gustavo.themovieapp.vm.MainViewModel

/**
 * Created by Gustavo on 16/09/17.
 */

open class BasicActivity(val modelClass: Class<out ViewModel>) : AppCompatActivity() {

    val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    fun getViewModel() : ViewModel = ViewModelProviders.of(this).get(modelClass)

    fun observeError(){
        (getViewModel() as BasicViewModel).error.observe(this, Observer<Error> { error ->
            showErrorDialog(error!!.menssagem!!)
        })
    }

    fun showErrorDialog(message : String){
        AlertDialog.Builder(this).setTitle("Problema").setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok") { dialog, id ->
                    dialog.cancel()
                }.create().show()
    }
}
