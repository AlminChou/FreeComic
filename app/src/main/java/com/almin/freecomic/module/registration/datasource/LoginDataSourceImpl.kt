package com.almin.freecomic.module.registration.datasource

import com.almin.freecomic.manager.UserManager
import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.freecomic.module.common.datasource.network.apiservice.UserApiService
import com.almin.freecomic.module.common.datasource.network.response.LoginResponse
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/6/22.
 */
class LoginDataSourceImpl(private val schedulerProvider: SchedulerProvider,
                          private val userApiService: UserApiService): LoginContract.DataSource{


    override fun login(username: String, password: String, httpResultSubscriber: HttpResultSubscriber<LoginResponse>) {
        userApiService.login(username,password)
                .map {
                    it.apply { UserManager.instance().userProfile = it.data }
                }
                .compose(schedulerProvider.asyncUIScheduler())
                .subscribe(httpResultSubscriber)
    }

}