package com.almin.freecomic

import android.content.Context
import android.content.pm.ApplicationInfo
import com.almin.library.AbstractConfiguration
import com.almin.library.util.NetworkUtils

/**
 * Created by Almin on 2018/6/11.
 */
class FcConfiguration private constructor(): AbstractConfiguration() {
    override val connectTimeout: Long
        get() =  10000L
    override val servicesHost: String
        get() = "https://"
    override val isNetworkAvailable: Boolean
        get() = NetworkUtils.isNetworkAvailable(FcApplication.instance())

    override fun getApiConfig(serverKey: String?): String? = null

    override fun isDebugBuild() = checkBuildType(FcApplication.instance())

    override fun isReleaseBuild() = !checkBuildType(FcApplication.instance())

    private fun checkBuildType(context: Context) =
            try { context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0 }
            catch (e: Exception) { false }


    companion object {
        private val instance = FcConfiguration()
        fun instance() = instance
    }

}