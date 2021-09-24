package com.shaadi.demo.data.network

import com.shaadi.demo.data.network.model.UserResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("?results=10")
    suspend fun getUserData(): UserResponse
}