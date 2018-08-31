package com.almin.library.imageloader

import android.widget.ImageView

/**
 * Created by Almin on 2018/6/12.
 *  策略模式 实现以及切换各种第三方图片loader
 */
interface ImageLoader {
    fun load(url: String, targetView: ImageView)

    fun loadPicBitmap(url: String)

    fun setUpImageLoaderCore(loaderCore: ImageLoaderCore)
}