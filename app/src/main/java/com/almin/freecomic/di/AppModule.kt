package com.almin.freecomic.di

import android.app.Application
import com.almin.freecomic.FcApplication
import com.almin.freecomic.FcConfiguration
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */

val applicationModule = module {

    single { FcApplication.instance() } bind Application::class

    single { FcConfiguration.instance() }

}

val appModules = listOf(applicationModule, apiServiceModule, registrationModule)