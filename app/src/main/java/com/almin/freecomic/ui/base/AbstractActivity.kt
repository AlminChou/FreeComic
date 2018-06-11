package com.almin.freecomic.ui.base

import android.support.v7.app.AppCompatActivity
import com.almin.freecomic.`interface`.FragmentOperator

/**
 * Created by Almin on 2018/6/8.
 */
abstract class AbstractActivity : AppCompatActivity(), FragmentOperator {

    override fun getCurrentFragment(): AbstractFragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragments(): List<AbstractFragment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPreviousFragment(): AbstractFragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(fragment: AbstractFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(fragment: AbstractFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popBackStackImmediate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popBackStackImmediate(name: String, flags: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
