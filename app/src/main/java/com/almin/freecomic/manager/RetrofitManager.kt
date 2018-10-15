package com.almin.freecomic.manager

import com.almin.freecomic.mvp.datasource.network.FcDynamicBaseUrlInterceptor
import com.almin.freecomic.mvp.datasource.network.FcUrlProcessor
import com.almin.freecomic.mvp.datasource.network.apiservice.UserApiService
import com.almin.library.network.retrofitlibrary.RetrofitClientProvider
import okhttp3.OkHttpClient

/**
 * Created by Almin on 2018/6/22.
 */
class RetrofitManager : RetrofitClientProvider(){

    companion object {
        private val instance = RetrofitManager()
        fun instance() = instance
    }

    override val baseUrl = "http://baseurl"


    override fun initService() {
        // do nothing , deliver Koin to do init
    }

    override fun addInterceptor(builder: OkHttpClient.Builder) {
        super.addInterceptor(builder)
        builder.addInterceptor(FcDynamicBaseUrlInterceptor(FcUrlProcessor()))
    }


}