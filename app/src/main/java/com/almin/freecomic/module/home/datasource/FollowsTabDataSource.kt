package com.almin.freecomic.module.home.datasource

import com.almin.freecomic.module.common.datasource.network.apiservice.ComicApiService
import com.almin.freecomic.module.home.contract.FollowsContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/10/18.
 */
class FollowsTabDataSource(private val schedulerProvider: SchedulerProvider,
                           private val commicApiService: ComicApiService): FollowsContract.DataSource{



    override fun getFollowList(userId: String, httpResultSubscriber: HttpResultSubscriber<String>) {
        commicApiService.getPersonalSubscription(userId)
                .compose(schedulerProvider.asyncUIScheduler())
                .subscribe(httpResultSubscriber)
    }


}