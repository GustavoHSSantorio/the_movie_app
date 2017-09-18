package com.example.gustavo.themovieapp.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.gustavo.themovieapp.controller.BasicController
import com.example.gustavo.themovieapp.model.Error

/**
 * Created by Gustavo on 16/09/17.
 */
open class BasicViewModel : ViewModel(){
    val error : MutableLiveData<Error> = MutableLiveData();
}