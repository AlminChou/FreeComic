package com.almin.freecomic.module.home.ui

import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.common.contract.AbstractContract

/**
 * Created by Almin on 2018/10/16.
 */
class DashboardTabFragment : AbstractTabFragment(){

    init{
        autoAttachToolbar = true
        layoutResourceId = R.layout.fragment_tab_dashboard
    }


    override val presenter: AbstractContract.Presenter = AbstractContract.Presenter.EMPTY




    override fun initView(view: View) {
        super.initView(view)

    }

    override fun initData() {

    }


    override fun reload() {
    }

}