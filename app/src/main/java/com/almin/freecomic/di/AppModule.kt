package com.almin.freecomic.di

import android.app.Application
import com.almin.freecomic.FcApplication
import com.almin.freecomic.FcConfiguration
import com.almin.freecomic.imageloader.FcImageLoader
import com.almin.freecomic.module.common.datasource.local.UserManager
import com.almin.library.imageloader.ImageLoader
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */

val applicationModule = module {

    single { FcApplication.instance() } bind Application::class

    single { FcConfiguration.instance() }

    single { FcImageLoader() } bind ImageLoader::class

}

val appModules = listOf(applicationModule, apiServiceModule, repositoryModule, registrationModule, homeModule, comicModule)