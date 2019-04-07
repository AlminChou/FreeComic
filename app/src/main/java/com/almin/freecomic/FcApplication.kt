package com.almin.freecomic

import com.almin.freecomic.di.appModules
import com.almin.freecomic.module.common.datasource.local.UserManager
import com.almin.freecomic.network.RetrofitManager
import com.almin.library.AbstractApplication
import com.almin.library.network.retrofitlibrary.TokenProvider
import org.koin.android.ext.android.inject
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

        Thread {
            startKoin(this,appModules)
            val tokenProvider: UserManager by inject()
            RetrofitManager.instance().init(FcConfiguration.instance(),tokenProvider)
        }.start()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}