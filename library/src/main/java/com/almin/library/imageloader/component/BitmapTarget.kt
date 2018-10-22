package com.almin.library.imageloader.component

import android.graphics.Bitmap

/**
 * Created by Almin on 2018/10/19.
 */
interface BitmapTarget {
    fun onBitmapFetch(bitmap: Bitmap)
}