package com.task.nebenan.service.rest


import com.sathish.examplelivedata.service.model.BlogWrapper
import retrofit2.Call
import retrofit2.http.*

/*
 **
 * Project Name : Task-android.
 * Created by : Sathish Kumar.R, Android Application Developer.
 * Created on : 22/7/2019
  * File Name : RetrofitInterface.java.
 * ClassName : RetrofitInterface.
  * Module Name : app.
 */






interface RetrofitInterface {


    /**
     * Request to neighbour list from api
     */


    @GET("/api/feed.json")
    fun  getMoviesData(): Call<BlogWrapper>


}
