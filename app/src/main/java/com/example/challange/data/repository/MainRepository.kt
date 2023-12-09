package com.example.challange.data.repository

import com.example.challange.data.remote.NetworkHandling
import com.example.challange.data.remote.RetrofitApi
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: RetrofitApi) {

    suspend fun latest() =
        NetworkHandling.toResultFlow { api.latest() }

}