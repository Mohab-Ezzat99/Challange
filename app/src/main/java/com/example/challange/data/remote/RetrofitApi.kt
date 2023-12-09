package com.example.challange.data.remote

import com.example.challange.data.remote.dto.ConvertResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET
    suspend fun convert(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int
    ) : Response<ConvertResponse>

}