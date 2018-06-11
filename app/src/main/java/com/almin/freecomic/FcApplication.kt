package com.almin.freecomic

import android.app.Application

/**
 * Created by Almin on 2018/6/8.
 */
class FcApplication : Application() {

    companion object {
        lateinit var instance : FcApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}