package com.almin.freecomic.imageloader

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by Almin on 2018/8/31.
 */
@GlideModule
class FcGlideModule : AppGlideModule(){
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        println("FcGlideModule    FcGlideModule    ")
        super.applyOptions(context, builder)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}