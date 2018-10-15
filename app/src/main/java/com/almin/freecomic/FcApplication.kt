package com.almin.freecomic

import com.almin.freecomic.di.appModules
import com.almin.freecomic.manager.RetrofitManager
import com.almin.library.AbstractApplication
import org.koin.android.ext.android.startKoin

/**
 * Created by Almin on 2018/6/8.
 */
class FcApplication : AbstractApplication(){

    companion object {
        private lateinit var instance : FcApplication
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin(this,appModules)

        RetrofitManager.instance().init(FcConfiguration.instance())
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}