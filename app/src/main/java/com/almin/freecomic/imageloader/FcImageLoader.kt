package com.almin.freecomic.imageloader

import android.graphics.Bitmap
import com.almin.library.imageloader.component.DisplayOptions
import com.almin.library.imageloader.component.ScaleType

/**
 * Created by Almin on 2018/6/22.
 */
class FcImageLoader : GlideImageLoader() {

    override val defaultDisplayOptions: DisplayOptions = DisplayOptions.create().let {
        it.scaleType = ScaleType.FIT_CENTER
        it.format = Bitmap.Config.ARGB_8888
//        it.placeholder =
//        it.error =
        it
    }

    override val needReferer = true

    override val refererHost = "http://images.dmzj.com/"



}