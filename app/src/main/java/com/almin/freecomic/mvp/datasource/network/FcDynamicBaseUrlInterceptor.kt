package com.almin.freecomic.mvp.datasource.network

import android.text.TextUtils
import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Almin on 2018/1/16.
 */

class FcDynamicBaseUrlInterceptor(private val mUrlProcessor: FcUrlProcessor) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        var requestUrl = request.url().toString()

        val baseUrl = mUrlProcessor.getBaseUrl(request.url().encodedPath())
        Log.d("Http request", String.format("Request http url base url change ：%s ", baseUrl))


        if (baseUrl != null) {
            val baseHttpUrl = HttpUrl.parse(baseUrl) ?: throw InvalidUrlException(baseUrl)
            val requestHttpUrl = mUrlProcessor.wrapUrl(baseHttpUrl, request.url())
            requestBuilder.url(requestHttpUrl)
            requestUrl = requestHttpUrl.toString()
        }

        mUrlProcessor.addHeader(requestBuilder)

        Log.d("Http request", String.format("Request http url is ： %s", requestUrl))

        return chain.proceed(requestBuilder.build())
    }

    private class InvalidUrlException(url: String) :
            RuntimeException("You've configured an invalid url : " +
                    if (TextUtils.isEmpty(url)) "EMPTY_OR_NULL_URL" else url)
}
