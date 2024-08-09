package com.project.suitmediaaryabagusf.api

import com.project.suitmediaaryabagusf.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("users")
    fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<UserResponse>

}