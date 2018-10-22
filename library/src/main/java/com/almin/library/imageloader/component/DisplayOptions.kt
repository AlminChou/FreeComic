package com.almin.library.imageloader.component

import android.graphics.Bitmap

/**
 * Created by Almin on 2018/10/19.
 */
class DisplayOptions(){
    var width: Int = 0
    var height: Int = 0
    var scaleType: ScaleType = ScaleType.FIT_CENTER
    var placeholder: Int = 0
    var error: Int = 0
    var format: Bitmap.Config = Bitmap.Config.ARGB_8888

    companion object {
        val DEFAULT = DisplayOptions()
        fun create() = DisplayOptions()
    }
}
