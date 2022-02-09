package com.example.katalogfilm_byfadli.vo

import com.example.katalogfilm_byfadli.vo.Status.*

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(SUCCESS, data, null)
        fun <T> error(message: String?, data: T?): Resource<T> = Resource(ERROR, data, message)
        fun <T> loading(data: T?): Resource<T> = Resource(LOADING, data, null)
    }
}