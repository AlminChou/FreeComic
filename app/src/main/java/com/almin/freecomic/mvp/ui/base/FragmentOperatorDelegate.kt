package com.almin.freecomic.mvp.ui.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Almin on 2018/6/12.
 */
class FragmentOperatorDelegate(private val fragmentManager: FragmentManager,
                               private val containerResId: Int,
                               private val fragmentMinCount: Int = 1): FragmentOperator{

    companion object {
        private const val ACTION_ADD = "add"
        private const val ACTION_REPLACE = "replace"
    }

    init {
        if (containerResId < 0 )
            throw IllegalArgumentException("containerResId can not found.")
    }

    override fun add(fragment: AbstractFragment, isAddToBackStack: Boolean, isAllowingStateLoss: Boolean) {
        operateFragmentTransaction(ACTION_ADD,fragment,isAddToBackStack,isAllowingStateLoss)
    }

    override fun replace(fragment: AbstractFragment, isAddToBackStack: Boolean, isAllowingStateLoss: Boolean) {
        operateFragmentTransaction(ACTION_REPLACE,fragment,isAddToBackStack,isAllowingStateLoss)
    }

    private fun operateFragmentTransaction(action: String, fragment: AbstractFragment, isAddToBackStack: Boolean, isAllowingStateLoss: Boolean) {
        val tag = fragment.transactionTag
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragment.enableAnimation) {
            fragmentTransaction.setCustomAnimations(fragment.enterAnimation, fragment.exitAnimation,
                    fragment.popEnterAnimation, fragment.popExitAnimation)
        }
        when(action){
            ACTION_ADD -> fragmentTransaction.add(containerResId,fragment,tag)
            ACTION_REPLACE -> fragmentTransaction.replace(containerResId,fragment,tag)
        }

        if(isAddToBackStack){
            fragmentTransaction.addToBackStack(tag)
        }
        if(isAllowingStateLoss){
            fragmentTransaction.commitAllowingStateLoss()
        }else{
            fragmentTransaction.commit()
        }
    }

    override fun remove() {

    }

    override fun hide() {

    }

    override fun popBackStackImmediate(): Boolean {
        if (fragmentManager.backStackEntryCount < 1) {
            return false
        }
        try {
            return fragmentManager.popBackStackImmediate()
        } catch (e: IllegalStateException) {
            return false
        }
    }

    override fun popBackStackImmediate(name: String, flags: Int): Boolean {
        if (fragmentManager.backStackEntryCount < 1) {
            return false
        }
        try {
            return fragmentManager.popBackStackImmediate(name, flags)
        } catch (e: IllegalStateException) {
            return false
        }
    }

    override fun getCurrentFragment(): AbstractFragment? {
        return fragmentManager.findFragmentById(containerResId) as AbstractFragment?
    }

    override fun getPreviousFragment(): AbstractFragment? {
        var previousFragment: AbstractFragment? = null
        val count = fragmentManager.backStackEntryCount
        if (count >= 2) {
            val backStackEntry = fragmentManager.getBackStackEntryAt(count - 2)
            previousFragment = fragmentManager.findFragmentByTag(backStackEntry.name) as AbstractFragment
        }
        return previousFragment
    }

    override fun getFragments(): List<AbstractFragment> {
        val count = fragmentManager.backStackEntryCount
        val fragments = ArrayList<AbstractFragment>(count)
        var tempFragment: Fragment
        var tempBackStackEntry: FragmentManager.BackStackEntry
        for (i in 0 until count) {
            tempBackStackEntry = fragmentManager.getBackStackEntryAt(i)
            tempFragment = fragmentManager.findFragmentByTag(tempBackStackEntry.name)
            if(tempFragment is AbstractFcFragment){
                fragments.add(tempFragment)
            }
        }
        return fragments
    }

    override fun handleBackPressed(): Boolean {
        var isFragmentHandle = false
        var currentFragment: AbstractFragment? = getCurrentFragment()
        val count = fragmentManager.backStackEntryCount
        if (count > fragmentMinCount) {
            if (currentFragment!!.onHandleGoBack()) {
                fragmentManager.popBackStackImmediate()
                currentFragment = getCurrentFragment()
                currentFragment!!.onPreviousPageBack()
                isFragmentHandle = true
            }
        }
        return isFragmentHandle
    }

}