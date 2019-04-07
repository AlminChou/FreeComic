package com.almin.freecomic.di

import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.freecomic.module.registration.datasource.LoginDataSource
import com.almin.freecomic.module.registration.presenter.LoginPresenter
import com.almin.library.scheduler.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/15.
 */
val registrationModule = module {

    factory { (view: LoginContract.ViewRenderer, schedulerProvider: SchedulerProvider) ->
        LoginPresenter(view, LoginDataSource(schedulerProvider, get(),get()))
    } bind LoginContract.Presenter::class

}