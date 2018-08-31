package com.almin.freecomic.mvp.contract

import com.almin.freecomic.mvp.datasource.network.response.LoginResponse
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/6/22.
 */
interface LoginContract{

    interface ViewRenderer: AbstractContract.ViewRenderer{
        fun onLoginSuccess()
        fun onLoginFailed(error: String)
        fun navigateToForgetPasswordPage()
    }

    interface Presenter: AbstractContract.Presenter{
        fun clickLogin(username: String, password: String)
    }

    interface DataSource: AbstractContract.DataSource{
        fun login(username: String,password: String, httpResultSubscriber: HttpResultSubscriber<LoginResponse>)
    }

}