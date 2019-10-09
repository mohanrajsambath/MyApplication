package com.sathish.examplelivedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sathish.examplelivedata.service.model.BlogWrapper
import com.sathish.examplelivedata.view.uitils.AppLog
import com.task.nebenan.service.rest.RetrofitClient
import com.task.nebenan.service.rest.RetrofitInterface
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {

    val movie = BlogRespos()
    var allBlog: MutableLiveData<BlogWrapper> = MutableLiveData<BlogWrapper>()


    fun callWebservice() {

                
        var apiservice: RetrofitInterface? =
            RetrofitClient.getClient.create(RetrofitInterface::class.java)
        var result: Call<BlogWrapper>? = apiservice!!.getMoviesData()

        result!!.enqueue(object : Callback, retrofit2.Callback<BlogWrapper> {
            override fun onFailure(call: Call<BlogWrapper>, t: Throwable) {
                AppLog.e("Exception",t.toString())

            }

            override fun onResponse(
                call: Call<BlogWrapper>,
                response: Response<BlogWrapper>
            ) {
                if (response.isSuccessful) {
                    allBlog.value= response.body()

                }

            }

        })


    }

}