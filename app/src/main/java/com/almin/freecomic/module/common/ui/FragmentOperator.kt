package com.almin.freecomic.module.common.ui


/**
 * Created by Almin on 2018/6/8.
 */
interface FragmentOperator {
    fun replace(fragment: AbstractFragment, isAddToBackStack: Boolean = true, isAllowingStateLoss: Boolean = true)

    fun add(fragment: AbstractFragment, isAddToBackStack: Boolean = true, isAllowingStateLoss: Boolean = true)

    fun remove()

    fun hide()

    fun popBackStackImmediate() : Boolean

    fun popBackStackImmediate(name: String, flags: Int): Boolean

    fun getCurrentFragment() : AbstractFragment?

    fun getPreviousFragment() : AbstractFragment?

    fun getFragments() : List<AbstractFragment>

    fun handleBackPressed(): Boolean

}