package com.example.presentation

sealed class Result {
    data class LoadingComplete(val items: List<Item>) : Result()
    data class LoadingError(val throwable: Throwable) : Result()
    object LoadingEmpty: Result()
    data class DisplayQueryToUser(val items: List<Item>) : Result()
}