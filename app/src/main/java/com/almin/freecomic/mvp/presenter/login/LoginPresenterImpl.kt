package com.almin.freecomic.mvp.presenter.login

import com.almin.freecomic.mvp.contract.LoginContract
import com.almin.freecomic.mvp.datasource.network.response.LoginResponse
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.network.retrofitlibrary.errorhandlecomponent.RetrofitException

/**
 * Created by Almin on 2018/6/22.
 */
class LoginPresenterImpl(private val viewRenderer: LoginContract.ViewRenderer,
                         private val dataSource: LoginContract.DataSource) : LoginContract.Presenter{

    override fun clickLogin(username: String, password: String) {
        dataSource.login(username, password, object : HttpResultSubscriber<LoginResponse>() {

            override fun onSuccess(response: LoginResponse) {
                 when(response.result){
                     1 -> viewRenderer.onLoginSuccess()
                     0 -> {
                         viewRenderer.onLoginFailed(response.msg)
                     }
                 }
            }

            override fun onError(retrofitException: RetrofitException) {
                viewRenderer.onLoginFailed(retrofitException.localizedMessage)
            }
        })
    }


    override fun start() {
    }

    override fun detach() {
    }
}