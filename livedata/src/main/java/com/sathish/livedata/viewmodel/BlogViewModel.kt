package com.sathish.livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sathish.livedata.service.model.BlogWrapper

class BlogViewModel : ViewModel() {
    lateinit var allblog:MutableLiveData<BlogWrapper>



}