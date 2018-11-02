package com.almin.freecomic.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextPaint
import android.text.TextUtils
import com.almin.freecomic.R
import com.almin.freecomic.module.home.adapter.FollowListAdapter
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Almin on 2018/10/23.
 */
class PinHeaderDecoration(var groupListener: PinHeaderDecoration.OnGroupListener) : RecyclerView.ItemDecoration() {

    private var groupHeight = 0

    private var headerTextPaint : TextPaint = TextPaint().let {
        it.isAntiAlias = true
        it.textSize = 70f
        it.textAlign = Paint.Align.LEFT
        it
    }

    private val headerBgPaint : Paint = Paint().let {
                it.color = Color.parseColor("#C9C9C9")
        it
    }



    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val top = (parent.top + parent.paddingTop).toFloat()
        val left = (parent.left + parent.paddingLeft).toFloat()
        val right = (parent.right - parent.paddingRight).toFloat()
        val layoutManager = (parent.layoutManager as GridLayoutManager)
        val firstViewPosition = layoutManager.findFirstVisibleItemPosition()

        if(groupListener.getGroupTitle(firstViewPosition).isEmpty()) return

        // for performance check
//        if(isSameGroup(firstViewPosition)) return

        layoutManager.findViewByPosition(firstViewPosition)?.let {
            val childView = layoutManager.findViewByPosition(firstViewPosition)
            val viewHolder = parent.getChildViewHolder(childView)
            if(viewHolder is FollowListAdapter.GroupHolder){
                groupHeight = childView.height
//                headerTextPaint.textSize = viewHolder.tvGroupTitle.textSize
//                headerTextPaint.color = viewHolder.tvGroupTitle.textColors.defaultColor
//                headerTextPaint.typeface = viewHolder.tvGroupTitle.typeface
                headerTextPaint = viewHolder.tvGroupTitle.paint


                val fontMetrics = headerTextPaint.fontMetrics
                val fontBaseline = groupHeight/2 + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom

                if(childView.y in (-groupHeight until 0)){
                    val rect = RectF()
                    rect.left = left
                    rect.top = top
                    rect.right = right
                    rect.bottom = groupHeight.toFloat()
                    c.drawRect(rect, headerBgPaint)
                    c.drawText(groupListener.getGroupTitle(firstViewPosition), parent.resources.getDimensionPixelOffset(R.dimen.dp_15).toFloat(), fontBaseline, headerTextPaint)
                }
            }else{
                val view = layoutManager.findViewByPosition(firstViewPosition)

                val bottom: Float = if(groupListener.isLastLineInGroup(firstViewPosition,layoutManager.spanCount)){
                    min(view.bottom,groupHeight).toFloat()
                }else{
                    groupHeight.toFloat()
                }

                val fontMetrics = headerTextPaint.fontMetrics
                val centerBaseLine = (groupHeight/2 + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom)
                val fontBaseLine = bottom - (groupHeight - centerBaseLine)


                val rect = RectF()
                rect.left = left
                rect.top = top
                rect.right = right
                rect.bottom =  bottom
                c.drawRect(rect, headerBgPaint)
                c.drawText(groupListener.getGroupTitle(firstViewPosition), parent.resources.getDimensionPixelOffset(R.dimen.dp_15).toFloat(),  fontBaseLine, headerTextPaint)
            }
        }

    }


    interface OnGroupListener{
        fun isLastLineInGroup(position: Int, spanCount: Int): Boolean
        fun getGroupTitle(firstViewPosition: Int): String
    }

    private fun isSameGroup(position: Int): Boolean{
        val preGroupTitle = groupListener.getGroupTitle(position-1)
        val currentGroupTitle = groupListener.getGroupTitle(position)
        return TextUtils.equals(preGroupTitle,currentGroupTitle)
    }
}

