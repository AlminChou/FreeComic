package com.almin.library.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivity.activeNetworkInfo
            if (info!!.isConnected) {
                return info.state == NetworkInfo.State.CONNECTED
            }
        } catch (e: Exception) {
        }

        return false
    }

}