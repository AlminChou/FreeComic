package com.almin.freecomic.mvp.ui.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.almin.freecomic.navigator.RegistrationNavigator
import com.almin.freecomic.mvp.ui.base.AbstractFcActivity

/**
 * Created by Almin on 2018/6/26.
 */
class RegistrationActivity : AbstractFcActivity(),RegistrationNavigator{


    companion object {
        fun go(activity: Activity){
            activity.startActivityForResult(Intent(activity,RegistrationActivity::class.java),
                    RegistrationNavigator.REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToLoginPage()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun navigateToLoginPage() {
        fragmentOperator.replace(LoginFragment.instance())
    }

    override fun navigateToSignUpPage() {
        fragmentOperator.replace(SignUpFragment.instance())
    }

    override fun navigateToForgotPage() {
        fragmentOperator.replace(ForgotPasswordFragment.instance())
    }

    override fun navigateToHomePage() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}