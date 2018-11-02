package com.almin.library.network.retrofitlibrary

import android.support.annotation.CallSuper
import com.almin.library.network.retrofitlibrary.errorhandlecomponent.RxErrorHandlingCallAdapterFactory
import com.almin.library.network.retrofitlibrary.interceptor.ConnectivityInterceptor
import com.almin.library.network.retrofitlibrary.ssl.TrustAllSSLSocketFactory
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Almin on 2017/12/25.
 */

abstract class RetrofitClientProvider {
    var configuration: RetrofitConfiguration? = null
    lateinit var retrofit: Retrofit
        private set

    lateinit var tokenProvider: TokenProvider

    protected abstract val baseUrl: String

    open protected val gsonConverter: Converter.Factory
        get() = GsonConverterFactory.create()


    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            initHttpClientConfig(builder)
            addInterceptor(builder)
            // Install the all-trusting trust manager
            builder.sslSocketFactory(TrustAllSSLSocketFactory.newInstance()!!)
            builder.hostnameVerifier { s, sslSession -> true }
            //      if(retrofitConfiguration.getServicesHost().contains("localhost")) {
            ////    }
            return builder.build()
        }

    fun init(retrofitConfiguration: RetrofitConfiguration, tokenProvider: TokenProvider) {
        this.configuration = retrofitConfiguration
        this.tokenProvider = tokenProvider
        initRetrofit()
        initService()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    protected abstract fun initService()

    @CallSuper
    protected fun initHttpClientConfig(builder: OkHttpClient.Builder) {
        builder.connectTimeout(configuration!!.connectTimeout, TimeUnit.MILLISECONDS)
        builder.readTimeout(configuration!!.connectTimeout, TimeUnit.MILLISECONDS)
    }

    @CallSuper
    protected open fun addInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(ConnectivityInterceptor(configuration!!))
    }


    protected fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    companion object {

        fun createCustomJsonRequestBody(json: String): RequestBody {
            return RequestBody.create(MediaType.parse("application/json"), json)
        }
    }

}
