package com.almin.library.imageloader

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.almin.library.imageloader.component.BitmapTarget
import com.almin.library.imageloader.component.CacheOptions
import com.almin.library.imageloader.component.DisplayOptions

/**
 * Created by Almin on 2018/6/12.
 *  策略模式 实现以及切换各种第三方图片loader
 */
interface ImageLoader {
    val defaultDisplayOptions: DisplayOptions
        get() = DisplayOptions.DEFAULT

    val defaultCacheOptions: CacheOptions
        get() = CacheOptions.DEFAULT

    val needReferer: Boolean
        get() = false

    val refererHost: String
        get() = ""

    companion object {
        const val REFERER_KEY = "Referer"
    }

    fun load(url: String,
             targetView: ImageView,
             displayOptions: DisplayOptions = defaultDisplayOptions,
             cacheOptions: CacheOptions = defaultCacheOptions)

    fun load(context: Context,
             url: String,
             targetView: ImageView,
             displayOptions: DisplayOptions = defaultDisplayOptions,
             cacheOptions: CacheOptions = defaultCacheOptions)

    fun load(activity: Activity,
             url: String,
             targetView: ImageView,
             displayOptions: DisplayOptions = defaultDisplayOptions,
             cacheOptions: CacheOptions = defaultCacheOptions)

    fun load(fragment: Fragment,
             url: String,
             targetView: ImageView,
             displayOptions: DisplayOptions = defaultDisplayOptions,
             cacheOptions: CacheOptions = defaultCacheOptions)

    fun loadPicBitmap(application: Application,
                      url: String,
                      bitmapTarget: BitmapTarget,
                      displayOptions: DisplayOptions = defaultDisplayOptions,
                      cacheOptions: CacheOptions = defaultCacheOptions)

    fun clearMemory(application: Application)

    fun clearDiskCache(application: Application)

}