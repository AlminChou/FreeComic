package com.almin.library.network.retrofitlibrary

interface ApiConfigProvider {
    fun getApiConfig(serverKey: String?): String?
}