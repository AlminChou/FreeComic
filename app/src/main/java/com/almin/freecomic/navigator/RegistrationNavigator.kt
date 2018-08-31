package com.almin.freecomic.navigator

/**
 * Created by Almin on 2018/6/26.
 */
interface RegistrationNavigator: Navigator{
    companion object {
        const val REQUEST_CODE = 111
    }

    fun navigateToLoginPage()
    fun navigateToSignUpPage()
    fun navigateToForgotPage()
    fun navigateToHomePage()
}