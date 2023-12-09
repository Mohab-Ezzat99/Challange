package com.example.challange.data.repository

import com.example.challange.data.remote.NetworkHandling
import com.example.challange.data.remote.RetrofitApi
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: RetrofitApi) {

    suspend fun convert(from: String, to: String, amount: Int) =
        NetworkHandling.toResultFlow { api.convert(from,to,amount) }

}