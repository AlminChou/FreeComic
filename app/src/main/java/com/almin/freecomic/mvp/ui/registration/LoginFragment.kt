package com.almin.freecomic.mvp.ui.registration

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.mvp.contract.AbstractContract
import com.almin.freecomic.mvp.contract.LoginContract
import com.almin.freecomic.mvp.datasource.LoginDataSourceImpl
import com.almin.freecomic.navigator.RegistrationNavigator
import com.almin.freecomic.mvp.presenter.login.LoginPresenterImpl
import com.almin.freecomic.mvp.ui.base.AbstractFcFragment
import com.almin.library.permission.PermissionGrantor
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by Almin on 2018/6/22.
 */
class LoginFragment : AbstractFcFragment(),LoginContract.ViewRenderer {

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var navigator : RegistrationNavigator

    init {
        layoutResourceId = R.layout.fragment_login
        menuResourceId = R.menu.login_page_menu
        pageTitle = "登录"
    }

    companion object {
        fun instance(): LoginFragment{
            val loginFragment = LoginFragment()
            loginFragment.arguments = null
            return loginFragment
        }
    }

    override fun bindPresenter(): AbstractContract.Presenter {
        presenter = LoginPresenterImpl(this, LoginDataSourceImpl(this))
        return presenter
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
                btn_login.isEnabled = et_username.text.isNotEmpty() and et_pwd.text.isNotEmpty()
            }
        }
        et_username.addTextChangedListener(textWatcher)
        et_pwd.addTextChangedListener(textWatcher)
        btn_login.isEnabled = false
        btn_login.setOnClickListener {
            presenter.clickLogin(et_username.text.toString().trim(),et_pwd.text.toString().trim())
        }

        permissionGrantor = PermissionGrantor.with(this).build()


        tv_forgot_pwd.setOnClickListener { navigateToForgetPasswordPage()}
    }

    lateinit var permissionGrantor : PermissionGrantor

    override fun initData() {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionGrantor.onRequestPermissionsResult(requestCode,permissions,grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_sign_up -> navigator.navigateToSignUpPage()
        }
        return super.onOptionsItemSelected(item)
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
}