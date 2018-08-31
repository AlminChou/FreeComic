package com.almin.freecomic.mvp.ui.base

import com.almin.library.ui.LifecycleFragment

/**
 * Created by Almin on 2018/6/11.
 */
abstract class AbstractFragment : LifecycleFragment(){

    protected var layoutResourceId: Int = -1

    protected var menuResourceId: Int = -1

    protected var pageTitle : String ? = null

    val transactionTag = javaClass.simpleName

    var enterAnimation: Int = 0

    var exitAnimation: Int = 0

    var popEnterAnimation: Int = 0

    var popExitAnimation: Int = 0

    var enableAnimation: Boolean = true

//    fun isPageCanGoBack(): Boolean = true

    fun onHandleGoBack(): Boolean = true

    fun onPreviousPageBack(){

    }
}