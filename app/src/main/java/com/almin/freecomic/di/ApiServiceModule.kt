package com.almin.freecomic.di

import com.almin.freecomic.manager.RetrofitManager
import com.almin.freecomic.mvp.datasource.network.apiservice.UserApiService
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 * Created by Almin on 2018/10/15.
 */
val apiServiceModule = module {

    single { RetrofitManager.instance().retrofit.create(UserApiService::class.java) }

}