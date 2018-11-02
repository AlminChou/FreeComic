package com.almin.freecomic.di

import com.almin.freecomic.network.RetrofitManager
import com.almin.freecomic.module.common.datasource.apiservice.ComicApiService
import com.almin.freecomic.module.common.datasource.apiservice.UserApiService
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */
val apiServiceModule = module {

    single { RetrofitManager.instance().retrofit.create(UserApiService::class.java) }

    single { RetrofitManager.instance().retrofit.create(ComicApiService::class.java) }
}