package com.almin.freecomic.imageloader

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.almin.library.imageloader.ImageLoader
import com.almin.library.imageloader.ImageLoader.Companion.REFERER_KEY
import com.almin.library.imageloader.component.*
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.security.MessageDigest

/**
 * Created by Almin on 2018/10/19.
 */
abstract class GlideImageLoader : ImageLoader {

    override fun loadPicBitmap(application: Application,
                               url: String,
                               bitmapTarget: BitmapTarget,
                               displayOptions: DisplayOptions,
                               cacheOptions: CacheOptions) {

        wrapperOptions(GlideApp.with(application.applicationContext).asBitmap().load(wrapperUrl(url)),displayOptions,cacheOptions)
//                .disallowHardwareConfig()
                .transform(object : BitmapTransformation() {
                    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
                    }

                    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
                        return bitmapTarget.onBitmapTransform(toTransform)
                    }
                })
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        bitmapTarget.onBitmapReady(resource)
                    }
                })
    }

    override fun load(url: String, targetView: ImageView, displayOptions: DisplayOptions, cacheOptions: CacheOptions) {
        wrapperOptions(GlideApp.with(targetView.context).load(wrapperUrl(url)),displayOptions,cacheOptions)
                .into(targetView)
    }

    override fun load(activity: Activity, url: String, targetView: ImageView, displayOptions: DisplayOptions, cacheOptions: CacheOptions) {
        wrapperOptions(GlideApp.with(activity).load(wrapperUrl(url)),displayOptions,cacheOptions)
                .into(targetView)
    }

    override fun load(fragment: Fragment, url: String, targetView: ImageView, displayOptions: DisplayOptions, cacheOptions: CacheOptions) {
        wrapperOptions(GlideApp.with(fragment).load(wrapperUrl(url)),displayOptions,cacheOptions)
                .into(targetView)
    }

    override fun load(context: Context, url: String, targetView: ImageView, displayOptions: DisplayOptions, cacheOptions: CacheOptions) {
        wrapperOptions(GlideApp.with(context).load(wrapperUrl(url)),displayOptions,cacheOptions)
                .into(targetView)
    }

    override fun clearMemory(application: Application) {
        GlideApp.get(application.applicationContext).clearMemory()
    }

    override fun clearDiskCache(application: Application) {
        Thread(Runnable {
            GlideApp.get(application.applicationContext).clearDiskCache()
        }).start()
    }

    private fun wrapperUrl(url: String) : GlideUrl =
            if(needReferer){
                GlideUrl(url, Headers { mapOf(REFERER_KEY to refererHost) })
            }else{
                GlideUrl(url)
            }

    private fun <T: Any> wrapperOptions(glideRequest: GlideRequest<T>, displayOptions: DisplayOptions, cacheOptions: CacheOptions): GlideRequest<T> {
        with(displayOptions){
            placeholder.let { if (it>0) glideRequest.placeholder(it)}
            error.let { if (it>0) glideRequest.error(it)}
            when (scaleType) {
                ScaleType.CENTER_CROP -> glideRequest.fitCenter()
                ScaleType.CIRCLE_CROP -> glideRequest.circleCrop()
                ScaleType.FIT_CENTER -> glideRequest.fitCenter()
                else -> glideRequest.fitCenter()
            }
            when (format) {
                Bitmap.Config.ARGB_8888 -> glideRequest.format(DecodeFormat.PREFER_ARGB_8888)
                Bitmap.Config.RGB_565 -> glideRequest.format(DecodeFormat.PREFER_RGB_565)
                else -> glideRequest.format(DecodeFormat.DEFAULT)
            }
            if((width > 0) and (height > 0)){
                glideRequest.override(width,height)
            }
        }


        with(cacheOptions){
            glideRequest.skipMemoryCache(!memoryCacheEnable)
            when(diskCacheType){
                DiskCacheType.ALL -> {
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
                }
                DiskCacheType.NULL -> {
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE)
                }
                DiskCacheType.ORIGINAL -> {
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA)
                }
                DiskCacheType.RESULT -> {
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                }
                else -> {
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                }
            }
        }

        return glideRequest
    }
}