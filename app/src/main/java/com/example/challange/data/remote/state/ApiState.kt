package com.example.challange.data.remote.state

sealed class ApiState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : ApiState<T>(data)
    class Error<T>(message: String) : ApiState<T>(message = message)
    class Success<T>(data: T?) : ApiState<T>(data = data)
}
