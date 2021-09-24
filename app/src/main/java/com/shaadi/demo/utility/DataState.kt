package com.shaadi.demo.utility

sealed class DataState<out R> {

    object Success : DataState<Nothing>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}