package com.almin.freecomic.module.home.datasource

import com.almin.freecomic.module.common.datasource.apiservice.ComicApiService
import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import com.almin.freecomic.module.common.datasource.repository.UserRepository
import com.almin.freecomic.module.home.contract.FollowsContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/10/18.
 */
class FollowsTabDataSource(private val schedulerProvider: SchedulerProvider,
                           private val comicApiService: ComicApiService,
                           private val userRepository: UserRepository): FollowsContract.DataSource{



    override fun getFollowList(httpResultSubscriber: HttpResultSubscriber<List<FollowInfo>>) {
        comicApiService.getPersonalSubscription(userRepository.getUserProfile()!!.uid)
                .compose(schedulerProvider.asyncUIScheduler())
                .subscribe(httpResultSubscriber)
    }


}