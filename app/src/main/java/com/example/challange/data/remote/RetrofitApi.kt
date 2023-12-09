package com.example.challange.data.remote

import com.example.challange.data.remote.dto.LatestResponse
import com.example.challange.data.remote.dto.SymbolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("symbols")
    suspend fun getSymbols() : Response<SymbolsResponse>

    @GET("latest")
    suspend fun latest() : Response<LatestResponse>

}