package com.almin.freecomic.module.home.ui

import android.support.design.widget.BottomNavigationView

/**
 * Created by Almin on 2018/6/11.
 */
interface TabDelegate {
    fun switchTab(tabId: Int, reload: Boolean = false)
    fun addTab(tab: Tab)
    fun replaceTab(tab: Tab)
    fun setUpTabNavigationView(navigation: BottomNavigationView)
    fun handleBackPressed(): Boolean
}