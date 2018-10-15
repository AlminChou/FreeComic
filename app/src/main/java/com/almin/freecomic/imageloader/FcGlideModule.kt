package com.almin.freecomic.imageloader

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions



/**
 * Created by Almin on 2018/8/31.
 */
@GlideModule
class FcGlideModule : AppGlideModule(){
    var diskCacheSize = 1024 * 1024 * 200 // 200mb
    var memorySize = Runtime.getRuntime().maxMemory().toInt() / 8  // 取1/8最大内存作为最大缓存

    override fun applyOptions(context: Context, builder: GlideBuilder) {

        //设置log级别
        builder.setLogLevel(Log.DEBUG)

        //设置编码格式
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))

        //默认请求选项
        builder.setDefaultRequestOptions(RequestOptions()
                        //设置等待时的图片
//                        .placeholder(R.drawable.img_loading)
//                        //设置加载失败后的图片显示
//                        .error(R.drawable.img_error)
                        .centerCrop()
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH))

        // 设置内存缓存
        val calculator = MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2f)
                .build()
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        //builder.setMemoryCache(new LruResourceCache(memorySize));//自定义大小

        //设置Bitmap 池
        val calculator2 = MemorySizeCalculator.Builder(context)
                .setBitmapPoolScreens(3f)
                .build()
        builder.setBitmapPool(LruBitmapPool(calculator2.bitmapPoolSize.toLong()))
        //builder.setBitmapPool(new LruBitmapPool(memorySize));//自定义大小

        //设置磁盘缓存【暂时不做处理】
        //lide 使用 DiskLruCacheWrapper 作为默认的 磁盘缓存 。 DiskLruCacheWrapper 是一个使用 LRU 算法的固定大小的磁盘缓存。默认磁盘大小为 250 MB ，位置是在应用的 缓存文件夹 中的一个 特定目录 。
        /*builder.setDiskCache(new ExternalDiskCacheFactory(context));
        builder.setDiskCache(new InternalDiskCacheFactory(context, diskCacheSize));*/

    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}