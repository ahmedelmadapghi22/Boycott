package com.example.boycott.data

sealed class Result<out T>(){
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val errMsg:Throwable) : Result<Nothing>()

}
