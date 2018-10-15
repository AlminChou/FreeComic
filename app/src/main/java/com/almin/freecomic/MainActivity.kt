package com.almin.freecomic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.almin.freecomic.module.common.ui.AbstractFcActivity
import com.almin.freecomic.module.home.ui.HomeActivity
import com.almin.freecomic.module.registration.ui.RegistrationActivity
import com.almin.freecomic.navigator.RegistrationNavigator


class MainActivity : AbstractFcActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_launch)

//        HomeActivity.start(this)
        RegistrationActivity.go(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            RegistrationNavigator.REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK -> {
                        HomeActivity.go(this)
                        finish()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    // 漫画页面用到
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            val decorView = window.decorView
//            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//        }
//    }
}
