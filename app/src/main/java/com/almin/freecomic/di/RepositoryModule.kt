package com.almin.freecomic.di

import com.almin.freecomic.module.common.datasource.local.UserManager
import com.almin.freecomic.module.common.datasource.repository.UserRepository
import com.almin.library.network.retrofitlibrary.TokenProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/22.
 */
val repositoryModule = module{

    single { UserManager() } bind UserRepository::class

}