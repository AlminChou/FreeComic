package com.almin.library.network.retrofitlibrary.interceptor


import android.text.TextUtils
import android.util.Log
import com.almin.library.network.retrofitlibrary.urlprocessor.DefaultUrlProcessor
import com.almin.library.network.retrofitlibrary.urlprocessor.UrlProcessor
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * Created by Almin on 2017/12/25.
 */

class DynamicBaseUrlInterceptor(private val mDefaultBaseUrl: String) : Interceptor {
    private val mUrlProcessorList = HashMap<String, UrlProcessor>()

    init {
        registerProcessor(DefaultUrlProcessor())
    }

    fun registerProcessor(urlProcessor: UrlProcessor) {
        mUrlProcessorList[urlProcessor.matchKey!!] = urlProcessor
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        var urlProcessor: UrlProcessor?

        val baseUrlKey = obtainBaseUrlKeyFromHeaders(request)
        var baseHttpUrl: HttpUrl? = null

        Log.d("Http request", String.format("Request http url base url key is ：%s ", baseUrlKey))


        if (!TextUtils.isEmpty(baseUrlKey)) {
            //remove the header key after obtain value
            requestBuilder.removeHeader(MATCH_HEADER_KEY)
            urlProcessor = getUrlProcessor(baseUrlKey)
            if (urlProcessor == null) {
                urlProcessor = getUrlProcessor(MATCH_KEY_DEFAULT)
            }
            baseHttpUrl = HttpUrl.parse(urlProcessor!!.baseUrl)
        } else {
            baseHttpUrl = HttpUrl.parse(mDefaultBaseUrl)
            urlProcessor = getUrlProcessor(MATCH_KEY_DEFAULT)
        }

        urlProcessor!!.addHeader(requestBuilder)

        if (null == baseHttpUrl) {
            throw InvalidUrlException(baseUrlKey)
        }

        val requestHttpUrl = urlProcessor.wrapUrl(baseHttpUrl, request.url())
        if (requestHttpUrl != null) {
            requestBuilder.url(requestHttpUrl)
            Log.d("Http request", String.format("Request http url is ： %s", requestHttpUrl.toString()))
        }


        return chain.proceed(requestBuilder.build())
    }


    private fun obtainBaseUrlKeyFromHeaders(request: Request): String? {
        val headers = request.headers(MATCH_HEADER_KEY)
        if (headers == null || headers.size == 0) {
            return null
        }
        if (headers.size > 1) {
            throw IllegalArgumentException("Duplicate baseUrl key definition in the headers")
        }
        return request.header(MATCH_HEADER_KEY)
    }

    private fun getUrlProcessor(matchKey: String?): UrlProcessor? {
        return mUrlProcessorList[matchKey]
    }

    private class InvalidUrlException constructor(url: String ?) : RuntimeException("You've configured an invalid url : " + if (TextUtils.isEmpty(url)) "EMPTY_OR_NULL_URL" else url)

    companion object {
        private val MATCH_HEADER_KEY = "base_url_key"
        private val MATCH_KEY_DEFAULT = "default"
    }
}
