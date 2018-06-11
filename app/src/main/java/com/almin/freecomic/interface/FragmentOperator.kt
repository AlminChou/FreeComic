package com.almin.freecomic.`interface`

import android.support.annotation.IdRes
import com.almin.freecomic.ui.base.AbstractFragment

/**
 * Created by Almin on 2018/6/8.
 */
interface FragmentOperator {
    fun replace(fragment: AbstractFragment)

    fun add(fragment: AbstractFragment)

    fun popBackStackImmediate() : Boolean

    fun popBackStackImmediate(name: String, flags: Int): Boolean

    fun getCurrentFragment() : AbstractFragment

    fun getPreviousFragment() : AbstractFragment

    @IdRes fun getContainerResId() : Int

    fun getFragments() : List<AbstractFragment>

}