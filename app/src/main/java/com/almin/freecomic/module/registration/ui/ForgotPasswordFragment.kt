package com.almin.freecomic.module.registration.ui

import android.view.View
import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.freecomic.module.common.ui.AbstractFcFragment

/**
 * Created by Almin on 2018/7/6.
 */
class ForgotPasswordFragment : AbstractFcFragment() {

    override val presenter: AbstractContract.Presenter = AbstractContract.Presenter.EMPTY

    companion object {
        fun instance() = ForgotPasswordFragment()
    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

}