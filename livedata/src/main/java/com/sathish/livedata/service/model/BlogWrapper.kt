package com.sathish.livedata.service.model


import com.google.gson.annotations.SerializedName

data class BlogWrapper(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("error")
    val error: Boolean = false, // false
    @SerializedName("message")
    val message: String = "", // AndroidWave RSS feed found
    @SerializedName("status")
    val status: String = "" // ok
)