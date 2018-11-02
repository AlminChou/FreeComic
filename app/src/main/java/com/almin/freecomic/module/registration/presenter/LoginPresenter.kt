package com.almin.freecomic.module.registration.presenter

import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.freecomic.module.common.datasource.model.response.LoginResponse
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.network.retrofitlibrary.errorhandlecomponent.RetrofitException

/**
 * Created by Almin on 2018/6/22.
 */
class LoginPresenter(private val viewRenderer: LoginContract.ViewRenderer,
                     private val dataSource: LoginContract.DataSource) : LoginContract.Presenter{

    override fun clickLogin(username: String, password: String) {
        dataSource.login(username, password, object : HttpResultSubscriber<LoginResponse>() {

            override fun onSuccess(response: LoginResponse) {
                 when(response.result){
                     LoginResponse.SUCCESS -> viewRenderer.onLoginSuccess()
                     LoginResponse.FAILED -> {
                         viewRenderer.onLoginFailed(response.msg)
                     }
                 }
            }

            override fun onError(retrofitException: RetrofitException) {
                viewRenderer.onLoginFailed(retrofitException.localizedMessage)
            }
        })
    }


    override fun start(t: Any?) {
        viewRenderer.disableLoginButton()
    }


    override fun checkLoginButtonEnable(account: String, password: String) {
        if(account.isEmpty() or password.isEmpty())
            viewRenderer.disableLoginButton()
        else
            viewRenderer.enableLoginButton()

    }

    override fun detach() {
    }
}