package com.almin.freecomic.module.home.ui

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager

/**
 * Created by Almin on 2018/10/17.
 */
class HomePageDelegate(private val containerId: Int, private val fragmentManager: FragmentManager): TabDelegate{

    companion object {
        const val MIN_TAB_SIZE = 1
    }

    private val tabs: MutableMap<String,Tab> = mutableMapOf()
    private lateinit var currentTab: Tab

    override fun addTab(tab: Tab) {
        if(!tabs.containsValue(tab)){
            tabs[tab.name] = tab
        }
    }

    override fun replaceTab(tab: Tab) {
        tabs[tab.name] = tab
    }

    override fun setUpTabNavigationView(navigation: BottomNavigationView) {
        navigation.setOnNavigationItemSelectedListener {
            switchTab(it.itemId)
            true
        }
        currentTab = tabs.values.first()
        show(currentTab)
    }

    // need refactor
    override fun switchTab(tabId: Int, reload: Boolean) {
        when(tabs.size > MIN_TAB_SIZE){
            true -> {
                hide(currentTab)
                show(tabs.filter { it.value.tabId == tabId }.values.first())
            }
            else -> {

            }
        }
    }


    private fun show(tab: Tab){
        with(fragmentManager.beginTransaction()){
            when(!tab.contentFragment.isAdded){
                true -> {
                    add(containerId,tab.contentFragment,tab.name)
                            .addToBackStack(tab.contentFragment.transactionTag)
                            .commitAllowingStateLoss()
                }
                else -> {
                    show(tab.contentFragment).commitAllowingStateLoss()
                }
            }
        }
        currentTab = tab
    }


    private fun hide(tab: Tab){
        with(fragmentManager.beginTransaction()){
            when(tab.contentFragment.isAdded){
                true -> {
//                    remove(tab.contentFragment).commitNowAllowingStateLoss()
                    hide(tab.contentFragment).commitAllowingStateLoss()
                }
                else -> {}
            }
        }
    }

    override fun handleBackPressed() = currentTab.contentFragment.onHandleGoBack()
}