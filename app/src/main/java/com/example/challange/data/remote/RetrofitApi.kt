package com.example.challange.data.remote

import com.example.challange.data.remote.dto.ConvertResponse
import com.example.challange.data.remote.dto.LatestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("latest")
    suspend fun latest() : Response<LatestResponse>

}