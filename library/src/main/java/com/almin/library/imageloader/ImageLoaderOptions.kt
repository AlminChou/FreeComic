package com.almin.library.imageloader

/**
 * Created by Almin on 2018/8/18.
 * provide some base loader options
 */
interface ImageLoaderOptions{

    fun setImageDecodeFormat()

    fun setDiskCacheOptions()

    fun setMemoryCacheOptions()

}