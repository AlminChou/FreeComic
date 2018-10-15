package com.almin.freecomic.di

import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.freecomic.module.registration.datasource.LoginDataSourceImpl
import com.almin.freecomic.module.registration.presenter.LoginPresenterImpl
import com.almin.library.scheduler.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */
val registrationModule = module {

    factory { (view : LoginContract.ViewRenderer, schedulerProvider: SchedulerProvider)
        ->
        LoginPresenterImpl(view, LoginDataSourceImpl(schedulerProvider, get()))
    } bind LoginContract.Presenter::class

}