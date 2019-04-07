package com.almin.freecomic.network


import android.support.v4.util.ArrayMap
import com.almin.library.network.retrofitlibrary.TokenProvider
import com.almin.library.network.retrofitlibrary.urlprocessor.DefaultUrlProcessor
import okhttp3.HttpUrl

/**
 * Created by Almin on 2018/1/16.
 */

class FcUrlProcessor(private val tokenProvider: TokenProvider) : DefaultUrlProcessor() {
    private val mBaseUrlMap : ArrayMap<String,String> = ArrayMap()

    init {
        mBaseUrlMap.apply {
            put("user_api_key","https://user.dmzj.com")
            put("v2_api_key","https://v2api.dmzj.com")
            put("download_api_key","https://imgzip.dmzj.com")
            put("interface_api_key","https://interface.dmzj.com")
            put("v3_api_key","https://v3api.dmzj.com")

        }
    }

    fun getBaseUrl(url: String): String? {
        val key = url.split("/")[1].takeIf { it.isNotEmpty() }
        return mBaseUrlMap[key]
    }

    override fun wrapUrl(baseHttpUrl: HttpUrl?, url: HttpUrl): HttpUrl {
        if (null == baseHttpUrl) return url
        val builder = url.newBuilder().removePathSegment(0)
        addParameter(builder)
        return builder.scheme(baseHttpUrl.scheme())
                .host(baseHttpUrl.host())
                .port(baseHttpUrl.port())
                .build()
    }

    override fun addParameter(builder: HttpUrl.Builder) {
        super.addParameter(builder)
        tokenProvider.getToken()?.let {
            builder.addQueryParameter("dmzj_token", tokenProvider.getToken())
            builder.addQueryParameter("channel","Android")
            builder.addQueryParameter("version","2.7.009")
        }
    }


}
