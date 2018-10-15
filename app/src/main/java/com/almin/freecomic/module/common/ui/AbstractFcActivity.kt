package com.almin.freecomic.module.common.ui

import android.os.Bundle

/**
 * Created by Almin on 2018/6/21.
 */
abstract class AbstractFcActivity : AbstractActivity() {

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

}