package com.almin.freecomic.module.home.ui

import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.common.contract.AbstractContract

/**
 * Created by Almin on 2018/10/18.
 */
class NewsTabFragment : AbstractTabFragment(){

    init{
        autoAttachToolbar = true
        layoutResourceId = R.layout.fragment_tab_news
    }


    override val presenter: AbstractContract.Presenter<Any?>? = AbstractContract.Presenter.EMPTY




    override fun initView(view: View) {

        super.initView(view)
    }

    override fun initData() {

    }


    override fun reload() {
    }

}