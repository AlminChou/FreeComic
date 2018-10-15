package com.almin.freecomic.di

import com.almin.freecomic.mvp.contract.LoginContract
import com.almin.freecomic.mvp.datasource.LoginDataSourceImpl
import com.almin.freecomic.mvp.presenter.login.LoginPresenterImpl
import com.almin.library.scheduler.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */
val registrationModule = module {

    factory { (view : LoginContract.ViewRenderer, schedulerProvider: SchedulerProvider)
        -> LoginPresenterImpl(view, LoginDataSourceImpl(schedulerProvider,get()))} bind LoginContract.Presenter::class

}