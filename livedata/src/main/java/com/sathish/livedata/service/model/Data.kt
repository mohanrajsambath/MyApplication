package com.sathish.livedata.service.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("author")
    val author: String = "", // admin
    @SerializedName("description")
    val description: String = "", // In the majority of these tutorials, we will focus on handling all kind of error of integrating REST APIs.
    @SerializedName("guid")
    val guid: String = "", // https://androidwave.com/?p=1353
    @SerializedName("link")
    val link: String = "", // https://androidwave.com/retrofit-globally-error-handling/
    @SerializedName("pubDate")
    val pubDate: String = "", // 2019-01-20 19:31:37
    @SerializedName("thumbnail")
    val thumbnail: String = "", // https://androidwave.com/wp-content/uploads/2019/01/retrofit-globally-error-handling-370x247.jpeg
    @SerializedName("title")
    val title: String = "" // Retrofit Globally Error Handling
)