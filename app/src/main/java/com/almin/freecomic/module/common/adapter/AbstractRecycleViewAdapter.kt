package com.almin.freecomic.module.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Almin on 2018/11/2.
 */
abstract class AbstractRecycleViewAdapter<Z: Any,T: RecyclerView.ViewHolder>: RecyclerView.Adapter<T>() {
    var onItemClickListener: OnItemClickListener<Z>? = null

    public interface OnItemClickListener<Z>{
        fun onItemClick(position: Int, any: Z?)
    }

    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.tag = holder.adapterPosition
        holder.itemView.setOnClickListener(onItemViewClickListener)
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
        holder.itemView.tag = null
    }

    private val onItemViewClickListener = View.OnClickListener { view ->
        view.tag?.let { tag ->
            val position: Int = tag as Int
            if(position!=RecyclerView.NO_POSITION){
                onItemClick(position)
            }
        }
    }


    protected open fun onItemClick(position: Int){
        onItemClickListener!!.onItemClick(position,null)
    }
}