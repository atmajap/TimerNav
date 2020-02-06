package com.example.timernav.ui.home

import com.example.timerretrofit.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @get:GET("posts")
    val posts: Call<List<Post>>

    @get:GET("ready")
    val ready: Call<List<Post>>
}
