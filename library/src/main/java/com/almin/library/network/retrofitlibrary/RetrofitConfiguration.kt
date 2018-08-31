package com.almin.library.network.retrofitlibrary


/**
 * Created by Almin on 2017/12/12.
 */

interface RetrofitConfiguration {
    abstract val connectTimeout: Long
    abstract val servicesHost: String
    abstract val isNetworkAvailable: Boolean
}
