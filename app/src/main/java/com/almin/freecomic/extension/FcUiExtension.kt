package com.almin.freecomic.extension

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.WindowManager


/**
 * Created by Almin on 2018/6/20.
 * define some ui extension method
 */

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this.context,message,duration).show()
}

fun Fragment.runOnUiThread(runnable: Runnable){
    this.activity!!.runOnUiThread(runnable)
}

fun Activity.runOnUiThread(runnable: Runnable){
    runOnUiThread(runnable)
}

fun Fragment.getStatusbarHeight() : Int{
    var result = 0
    val resourceId = context!!.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context!!.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getDisplayWidth() : Int{
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getSize(point)
    return point.x
}

fun Context.getDisplayHeight() : Int{
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getSize(point)
    return point.y
}



