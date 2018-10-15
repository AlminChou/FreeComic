package com.almin.freecomic.module.registration.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.registration.contract.LoginContract
import com.almin.freecomic.module.common.ui.AbstractFcFragment
import com.almin.freecomic.navigator.RegistrationNavigator
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Almin on 2018/6/22.
 */
class LoginFragment : AbstractFcFragment(), LoginContract.ViewRenderer {

    override val presenter: LoginContract.Presenter by inject{ parametersOf(this,this) }

    private lateinit var navigator : RegistrationNavigator

    init {
        layoutResourceId = R.layout.fragment_login
        menuResourceId = R.menu.login_page_menu
        pageTitle = "登录"
    }

    companion object {
        fun instance(): LoginFragment {
            val loginFragment = LoginFragment()
            loginFragment.arguments = null
            return loginFragment
        }
    }


    override fun onFcAttatch(context: Context) {
        if (context is RegistrationNavigator) navigator = context
    }

    override fun initView(view: View) {
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                presenter.checkLoginButtonEnable(et_username.text.toString().trim(),et_pwd.text.toString().trim())
            }
        }

        et_username.addTextChangedListener(textWatcher)
        et_pwd.addTextChangedListener(textWatcher)
        btn_login.setOnClickListener {
            presenter.clickLogin(et_username.text.toString().trim(),et_pwd.text.toString().trim())
        }

        tv_forgot_pwd.setOnClickListener { navigateToForgetPasswordPage()}
    }

    override fun initData() {
        presenter.start(Unit)
    }

    override fun disableLoginButton() {
        btn_login.isEnabled = false
    }

    override fun enableLoginButton() {
        btn_login.isEnabled = true
    }

    override fun onLoginSuccess() {
        navigator.navigateToHomePage()
    }

    override fun onLoginFailed(error: String) {
        showToast(error)
    }

    override fun navigateToForgetPasswordPage() {
        navigator.navigateToForgotPage()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_sign_up -> navigator.navigateToSignUpPage()
        }
        return super.onOptionsItemSelected(item)
    }
}