package com.example.boycott.domain

sealed class Result {
    data class Success<T>(val data: T) : Result()
    data class Error(val message: String) : Result()
}
