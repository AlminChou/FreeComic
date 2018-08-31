package com.almin.freecomic.extension

import android.content.Context
import android.os.Build

/**
 * Created by Almin on 2018/6/22.
 */
inline fun Context.afterLolipop(action:() -> Unit){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        action()
    }
}

inline fun android.support.v4.app.Fragment.afterLolipop(action:() -> Unit){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        action()
    }
}

inline fun Context.afterKikat(action:() -> Unit){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        action()
    }
}

inline fun android.support.v4.app.Fragment.afterKikat(action:() -> Unit){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        action()
    }
}
