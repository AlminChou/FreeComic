package com.almin.freecomic.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.widget.FrameLayout

/**
 * Created by Almin on 2018/10/18.
 */
class WindowInsetsFrameLayout : FrameLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs){
        setOnHierarchyChangeListener(object : OnHierarchyChangeListener{
            override fun onChildViewAdded(parent: View?, child: View?) {
                requestApplyInsets()
            }

            override fun onChildViewRemoved(parent: View?, child: View?) {

            }

        })
    }


    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        for (i in 0 until childCount){
            getChildAt(i).dispatchApplyWindowInsets(insets)
        }
//        return super.onApplyWindowInsets(insets)
        return insets!!
    }

}