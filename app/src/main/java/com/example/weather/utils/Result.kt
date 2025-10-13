package com.example.weather.utils

import android.os.Message

sealed class Result<out T>{

    data object Initial : Result<Nothing>()
    data object Loading : Result<Nothing>()

    data class Success<T>(val data : T) : Result<T>()
    data class Error(val message: Any) : Result<Nothing>()


}