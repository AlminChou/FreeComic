package com.almin.freecomic.module.home.ui

import com.almin.freecomic.module.common.ui.AbstractFcFragment

/**
 * Created by Almin on 2018/10/16.
 */
abstract class AbstractTabFragment: AbstractFcFragment(){


    init{
//        autoAttachToolbar = false
    }

    override fun onHandleGoBack(): Boolean {
        println(" onHandleGoBack    $transactionTag    ")
        return false
    }


    abstract fun reload()
}