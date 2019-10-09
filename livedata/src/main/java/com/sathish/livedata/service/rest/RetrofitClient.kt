package com.task.nebenan.service.rest



import com.sathish.livedata.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Sathish Kumar on 19/07/19,19,.
 */


 class RetrofitClient  {


    companion object {

        var retrofit: Retrofit? = null

         val getClient: Retrofit
            get() {
                if(retrofit==null){
                    retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okhttpClient)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                }
                return retrofit!!

            }

        val okhttpClient: OkHttpClient
            get() {
                var interceptor: HttpLoggingInterceptor?= HttpLoggingInterceptor()
                interceptor!!.level = HttpLoggingInterceptor.Level.BODY
                return if (!BuildConfig.RETROFIT_LOG_INTERCEPTOR){
                    OkHttpClient.Builder().build()
                }else{
                    try {
                        OkHttpClient.Builder().addInterceptor(interceptor).build()
                    } finally {
                    }
                }


            }
    }





}