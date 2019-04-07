package com.almin.freecomic.di

import com.almin.freecomic.module.comic.contract.ComicInfoContract
import com.almin.freecomic.module.comic.datasource.ComicInfoDataSource
import com.almin.freecomic.module.comic.presenter.ComicInfoPresenter
import com.almin.library.scheduler.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by Almin on 2018/11/14.
 */
val comicModule = module {

    factory { (view: ComicInfoContract.ViewRenderer, schedulerProvider: SchedulerProvider)  ->
        ComicInfoPresenter(view, ComicInfoDataSource(schedulerProvider,get()))
    } bind ComicInfoContract.Presenter::class


}