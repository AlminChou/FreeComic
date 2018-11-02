package com.almin.freecomic.module.registration.datasource

import com.almin.freecomic.module.common.datasource.apiservice.UserApiService
import com.almin.freecomic.module.common.datasource.model.UserProfile
import com.almin.freecomic.module.common.datasource.model.response.LoginResponse
import com.almin.freecomic.module.common.datasource.repository.UserRepository
import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/6/22.
 */
class LoginDataSource(private val schedulerProvider: SchedulerProvider,
                      private val userApiService: UserApiService,
                      private val userRepository: UserRepository): LoginContract.DataSource{


    override fun login(username: String, password: String, httpResultSubscriber: HttpResultSubscriber<LoginResponse>) {
//        userApiService.login(username,password)
//                .map {
//                    it.apply { userRepository.updateUserProfile(it.data) }
//                }
//                .compose(schedulerProvider.asyncUIScheduler())
//                .subscribe(httpResultSubscriber)

//            "data": {
//            "uid": "102018822",
//            "nickname": "Almin",
//            "dmzj_token": "97de3098ebda29311386940a372c34d3",
//            "photo": "http:\/\/images.dmzj.com\/user\/39\/13\/391302b597d30a49937f946402bce60f.png",
//            "bind_phone": "13660096015",
//            "email": "",

        val userProfile = UserProfile("102018822","almin",
                "97de3098ebda29311386940a372c34d3",
                "http:\\/\\/images.dmzj.com\\/user\\/39\\/13\\/391302b597d30a49937f946402bce60f.png","","","")
        userRepository.updateUserProfile(userProfile)
        val loginResponse = LoginResponse(userProfile,1,"")
        httpResultSubscriber.onSuccess(loginResponse)
    }

}