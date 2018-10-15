package com.almin.freecomic.mvp.ui.registration

import android.view.View
import com.almin.freecomic.mvp.contract.AbstractContract
import com.almin.freecomic.mvp.ui.base.AbstractFcFragment

/**
 * Created by Almin on 2018/7/6.
 */
class ForgotPasswordFragment : AbstractFcFragment() {

    override val presenter: AbstractContract.Presenter<Unit> = AbstractContract.Presenter.EMPTY

    companion object {
        fun instance() = ForgotPasswordFragment()
    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

}