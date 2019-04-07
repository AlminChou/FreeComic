package com.almin.library.imageloader.component

/**
 * Created by Almin on 2018/10/19.
 */
//enum class DiskCacheType {
//    ALL,
//    NULL,
//    ORIGINAL,
//    RESULT,
//    DEFAULT
//}
sealed class DiskCacheType{
    object ALL: DiskCacheType()
    object NULL: DiskCacheType()
    object ORIGINAL: DiskCacheType()
    object RESULT: DiskCacheType()
    object DEFAULT: DiskCacheType()
}