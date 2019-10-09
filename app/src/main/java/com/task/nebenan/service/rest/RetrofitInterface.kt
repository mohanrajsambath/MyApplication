package com.task.nebenan.service.rest

import com.task.nebenan.service.model.AndroidWave
import okhttp3.ResponseBody
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

    @POST("api/v2/neighbours.json")
    fun getNeighbourList(@Header("X-AUTH-TOKEN") accept: String, @Header("Authorization") authorization: String)


    @GET("/api/feed.json")
       fun getAndroidWave(): Call<AndroidWave>


}