package com.example.challange.data.remote

import com.example.challange.data.remote.state.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

object NetworkHandling {

    private lateinit var status: Status

    fun observeNetworkStatus(status: Status) {
        this.status = status
    }

    fun <T> toResultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {
        emit(ApiState.Loading())
        try {
            val response = call()
            if (response.isSuccessful) emit(ApiState.Success(response.body()))
            else emit(ApiState.Error(""))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(ApiState.Error("No internet"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiState.Error("Error"))
        }
    }

    interface Status {
        fun onBadRequest(exception: String?)
        fun onMakeAction(exception: String?)
        fun onNotVerifyRequest(exception: String?)
        fun onNotAuthorized(exception: String?)
        fun onServerSideError(exception: String?)
        fun <T> onDynamicCode(response: Response<T>)
        fun onConnectionFail(exception: String?)
        fun onNotAllowed()
        fun onApiNotFound()
        fun onNoInternet()
    }

}