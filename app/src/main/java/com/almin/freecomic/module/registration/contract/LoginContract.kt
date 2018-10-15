package com.almin.freecomic.module.registration.contract

import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.freecomic.module.common.datasource.network.response.LoginResponse
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/6/22.
 */
interface LoginContract{

    interface ViewRenderer: AbstractContract.ViewRenderer {
        fun enableLoginButton()
        fun disableLoginButton()
        fun onLoginSuccess()
        fun onLoginFailed(error: String)
        fun navigateToForgetPasswordPage()
    }

    interface Presenter: AbstractContract.Presenter<Unit> {
        fun clickLogin(username: String, password: String)
        fun checkLoginButtonEnable(account: String, password: String)
    }

    interface DataSource: AbstractContract.DataSource {
        fun login(username: String,password: String, httpResultSubscriber: HttpResultSubscriber<LoginResponse>)
    }

}