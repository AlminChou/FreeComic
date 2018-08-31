package com.almin.library.network.retrofitlibrary.urlprocessor

import okhttp3.HttpUrl
import okhttp3.Request

/**
 * Created by Almin on 2017/12/25.
 */

interface UrlProcessor {

    val matchKey: String ?

    val baseUrl: String ?

    val scheme: String ?

    val host: String ?

    fun addParameter(builder: HttpUrl.Builder)

    fun addHeader(builder: Request.Builder)

    fun wrapUrl(baseHttpUrl: HttpUrl?, url: HttpUrl): HttpUrl

    companion object {
        val MATCH_HEADER = "base_url_key:"
    }
}
