package com.almin.freecomic.mvp.ui.base

import android.os.Bundle
import com.almin.freecomic.R
import com.almin.freecomic.navigator.Navigator
import com.almin.library.ui.LifecycleActivity

/**
 * Created by Almin on 2018/6/8.
 */
abstract class AbstractActivity : LifecycleActivity(),Navigator {

    protected var containerResId: Int = R.id.fl_container
    protected var layoutResId: Int = R.layout.activity_container
    protected var pageTitle: String? = null
    protected var fragmentMinCount: Int = 1

    protected lateinit var fragmentOperator : FragmentOperator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        // init fragmentOperator
        fragmentOperator = FragmentOperatorDelegate(supportFragmentManager,containerResId,fragmentMinCount)
    }

    override fun onBackPressed() {
        if(!fragmentOperator.handleBackPressed()){
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    override fun back() {
        onBackPressed()
    }

    override fun backToTagetPage(target: String) {
    }


}

