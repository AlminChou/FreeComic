package com.almin.freecomic.module.home.ui

import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.common.contract.AbstractContract

/**
 * Created by Almin on 2018/10/18.
 */
class FollowsTabFragment  : AbstractTabFragment(){

    init{
        autoAttachToolbar = true
        layoutResourceId = R.layout.fragment_tab_follows
    }


    override val presenter: AbstractContract.Presenter<Unit>? = AbstractContract.Presenter.EMPTY




    override fun initView(view: View) {


    }

    override fun initData() {

    }


    override fun reload() {
    }

}