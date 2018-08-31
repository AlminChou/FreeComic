package com.almin.library.network.retrofitlibrary.interceptor


import com.almin.library.network.retrofitlibrary.RetrofitConfiguration
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Almin on 2017/12/12.
 */

class ConnectivityInterceptor(private val retrofitConfiguration: RetrofitConfiguration) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!retrofitConfiguration.isNetworkAvailable) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }


    class NoConnectivityException : IOException() {
        override val message: String?
            get() = "No connectivity exception"
    }

}
