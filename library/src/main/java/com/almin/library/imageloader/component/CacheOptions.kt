package com.almin.library.imageloader.component

/**
 * Created by Almin on 2018/10/19.
 */
class CacheOptions{
    var diskCacheType : DiskCacheType = DiskCacheType.DEFAULT
    val memoryCacheEnable: Boolean = true

    companion object {
        val DEFAULT = CacheOptions()
        fun create() = CacheOptions()
    }
}