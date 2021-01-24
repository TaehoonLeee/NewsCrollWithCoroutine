package com.example.newscroll.model

sealed class Result<T>(
    val status : Status,
    val data : T? = null,
    val message : String? = null
) {
    class Success<T>(data : T) : Result<T>(Status.SUCCESS, data)

    class Loading<T>(data : T?) : Result<T>(Status.LOADING, data)

    class Error<T>(message: String, data: T? = null) : Result<T>(Status.ERROR, data, message)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}