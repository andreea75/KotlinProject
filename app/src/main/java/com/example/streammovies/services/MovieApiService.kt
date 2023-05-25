package com.cronocode.moviecatalog.services

import com.example.streammovies.response.PostModel
import retrofit2.Call
import retrofit2.http.*


interface MovieApiService {
    @GET("posts")
    fun getPosts(): Call<ArrayList<PostModel>>
}