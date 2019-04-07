package com.almin.library.imageloader.component

/**
 * Created by Almin on 2018/10/19.
 */
//enum class ScaleType {
//    FIT_CENTER,CENTER_CROP,CIRCLE_CROP
//}

sealed class ScaleType{
    object FIT_CENTER: ScaleType()
    object CENTER_CROP: ScaleType()
    object CIRCLE_CROP: ScaleType()
}
