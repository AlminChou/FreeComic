package com.almin.freecomic.module.home.ui

import android.support.annotation.CallSuper
import android.view.View
import com.almin.freecomic.module.common.ui.AbstractFcFragment
import kotlinx.android.synthetic.main.root_toolbar_layout.*

/**
 * Created by Almin on 2018/10/16.
 */
abstract class AbstractTabFragment: AbstractFcFragment(){


    init{
//        autoAttachToolbar = false
    }

    override fun onHandleGoBack(): Boolean {
        println(" onHandleGoBack    $transactionTag    ")
        return true
    }


    abstract fun reload()


    @CallSuper
    override fun initView(view: View) {
        setUpDrawerToggle()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        println(" $transactionTag  hidden :    "+hidden)

        when(hidden){
            true -> { }
            else -> { setUpDrawerToggle() }
        }

    }

    private fun setUpDrawerToggle(){
        toolbar.tag = transactionTag
        toolbar?.let { (activity as HomeActivity).initDrawer(toolbar) }
    }
}