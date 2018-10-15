package com.almin.freecomic.di

import com.almin.freecomic.manager.RetrofitManager
import com.almin.freecomic.module.common.datasource.network.apiservice.UserApiService
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */
val apiServiceModule = module {

    single { RetrofitManager.instance().retrofit.create(UserApiService::class.java) }

}