package com.almin.freecomic.di

import com.almin.freecomic.module.home.contract.FollowsContract
import com.almin.freecomic.module.home.datasource.FollowsTabDataSource
import com.almin.freecomic.module.home.presenter.FollowsTabPresenter
import com.almin.library.scheduler.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/10/22.
 */
val homeModule = module{

    factory {
        (view: FollowsContract.ViewRenderer, schedulerProvider: SchedulerProvider)  ->
        FollowsTabPresenter(view,FollowsTabDataSource(schedulerProvider,get(),get()))
    } bind FollowsContract.Presenter::class



}