package com.sathish.examplelivedata.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sathish.examplelivedata.service.model.Blog

class BlogRespos {


    var movies = mutableListOf<Blog>()
   // var mutableLiveData = MutableLiveData<List<Blog>>()


    fun getMutableLiveData():MutableLiveData<List<Blog>> {


        return MutableLiveData()
    }

}
