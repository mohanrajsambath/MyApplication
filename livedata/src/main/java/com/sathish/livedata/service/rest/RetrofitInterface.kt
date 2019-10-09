package com.sathish.livedata.service.rest

import com.sathish.livedata.service.model.BlogWrapper
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET
        ("/api/feed.json")
          fun  getBlogData() :  Call<BlogWrapper>

}