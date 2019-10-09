package com.task.nebenan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){

    val currentRandomNumber :MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }



}