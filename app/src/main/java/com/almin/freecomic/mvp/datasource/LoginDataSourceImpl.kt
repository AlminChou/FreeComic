package com.almin.freecomic.mvp.datasource

import com.almin.freecomic.mvp.contract.LoginContract
import com.almin.freecomic.mvp.datasource.network.response.LoginResponse
import com.almin.freecomic.manager.RetrofitManager
import com.almin.freecomic.manager.UserManager
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/6/22.
 */
class LoginDataSourceImpl(private val schedulerProvider: SchedulerProvider): LoginContract.DataSource{

    private val userApiService =  RetrofitManager.instance().userApiService

    override fun login(username: String, password: String, httpResultSubscriber: HttpResultSubscriber<LoginResponse>) {
        userApiService.login(username,password)
                .map {
                    it.apply { UserManager.instance().userProfile = it.data }
                }
                .compose(schedulerProvider.asyncUIScheduler())
                .subscribe(httpResultSubscriber)
    }

}