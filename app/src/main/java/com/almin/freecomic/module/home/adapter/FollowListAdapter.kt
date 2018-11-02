package com.almin.freecomic.module.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.almin.freecomic.R
import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import com.almin.freecomic.widget.PinHeaderDecoration
import com.almin.library.imageloader.ImageLoader

/**
 * Created by Almin on 2018/10/23.
 */
class FollowListAdapter(private val imageLoader: ImageLoader,
                        private var followList: MutableList<FollowInfo>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), PinHeaderDecoration.OnGroupListener {

    companion object {
        private const val GROUP_NAME_UPDATE = "已更新"
        private const val GROUP_NAME_ALREADY = "已阅读"
    }


    private val emptyUpdateItemData: FollowInfo = FollowInfo("empty_update")
    private val emptyAlreadyItemData: FollowInfo = FollowInfo("already_read_empty")
    private val updateHeaderItemData: FollowInfo = FollowInfo("header_update")
    private val alreadyHeaderItemData: FollowInfo = FollowInfo("header_already")


    init{
        rebuildData()
    }


    private lateinit var updateList: List<FollowInfo>
    private lateinit var alreadyReadList: List<FollowInfo>


    private fun rebuildData() {

        // rebuild group data

        //for test
//        updateList = emptyList()
//        alreadyReadList = emptyList()

        updateList = followList.filter { it.sub_readed != FollowInfo.STATE_READED }
        alreadyReadList = followList.filter { it.sub_readed == FollowInfo.STATE_READED }

        followList.clear()


        // update header
        followList.add(updateHeaderItemData)

        // group update
        if(updateList.isEmpty()){
            followList.add(emptyUpdateItemData)
        }
        followList.addAll(updateList)

        // group already read
        followList.add(alreadyHeaderItemData)
        if(alreadyReadList.isEmpty()){
            followList.add(emptyAlreadyItemData)
        }
        followList.addAll(alreadyReadList)
    }

    override fun getItemViewType(position: Int): Int {
        return when(followList[position].name){
            updateHeaderItemData.name -> GroupHolder.type
            emptyUpdateItemData.name -> EmptyHolder.type
            alreadyHeaderItemData.name -> GroupHolder.type
            emptyAlreadyItemData.name -> EmptyHolder.type
            else -> Holder.type
        }
    }

    override fun getItemCount(): Int = followList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            GroupHolder.type -> {
                GroupHolder(LayoutInflater.from(parent.context).inflate(GroupHolder.layoutId,parent,false))
            }
            Holder.type -> {
                Holder(LayoutInflater.from(parent.context).inflate(Holder.layoutId,parent,false))
            }
            else -> {
                EmptyHolder(LayoutInflater.from(parent.context).inflate(EmptyHolder.layoutId,parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Holder -> bindComicHolder(holder,position)
            is GroupHolder -> bindGroupHolder(holder,position)
            is EmptyHolder -> bindEmptyHolder(holder,position)
        }
    }

    private fun bindEmptyHolder(holder: EmptyHolder, position: Int) {
        when(followList[position]){
            emptyUpdateItemData -> {
                holder.ivEmptyIcon.setImageResource(R.mipmap.bg_follow_empty_update)
            }
            emptyAlreadyItemData -> {
                holder.ivEmptyIcon.setImageResource(R.mipmap.bg_follow_empty_follow)
            }
        }
    }

    private fun bindGroupHolder(holder: GroupHolder, position: Int) {
        when(followList[position]){
            alreadyHeaderItemData -> {
                holder.tvGroupTitle.text = GROUP_NAME_ALREADY
            }
            updateHeaderItemData -> {
                holder.tvGroupTitle.text = GROUP_NAME_UPDATE
            }
        }

    }

    private fun bindComicHolder(holder: Holder, position: Int) {
        with(followList[position]){
            holder.tvComicName.text = name
            holder.tvComicUpdate.text = String.format("更新：$sub_update")
            holder.tvComicMark.text = String.format("状态：$status")
            imageLoader.load(holder.itemView.context,sub_img,holder.ivComicPic)
        }
    }

    fun isGroupPosition(position: Int): Boolean {
        return when(followList[position]){
            emptyUpdateItemData -> true
            emptyAlreadyItemData -> true
            alreadyHeaderItemData -> true
            updateHeaderItemData -> true
            else -> false
        }
    }

    override fun getGroupTitle(position: Int): String {
        val itemData = if(position < 0) null else followList[position]
        return when(itemData){
            in updateList ->{
                GROUP_NAME_UPDATE
            }
            in alreadyReadList -> {
                GROUP_NAME_ALREADY
            }
            updateHeaderItemData ->{
                GROUP_NAME_UPDATE
            }
            alreadyHeaderItemData ->{
                GROUP_NAME_ALREADY
            }
            emptyUpdateItemData -> {
                GROUP_NAME_UPDATE
            }
            emptyAlreadyItemData -> {
                GROUP_NAME_ALREADY
            }
            else ->{""}
        }
    }

    override fun isLastLineInGroup(position: Int, spanCount: Int): Boolean {
        val itemData = followList[position]
        return when(itemData){
            in updateList -> {
                isLastLineInGroup(updateList,itemData,spanCount)
            }
            in alreadyReadList -> {
                isLastLineInGroup(alreadyReadList, itemData, spanCount)
            }
            emptyUpdateItemData -> {
                true
            }
            emptyAlreadyItemData -> {
                true
            }
            else -> false
        }
    }

    private fun isLastLineInGroup(followGroupList: List<FollowInfo>, followInfo: FollowInfo, spanCount: Int): Boolean{
        return if(followGroupList.size <= spanCount){
            true
        }else{
            val lineCount = if(followGroupList.size % spanCount == 0) followGroupList.size / spanCount else (followGroupList.size / spanCount)+1
            val itemPositionForGroup = followList.indexOf(followInfo) + 1
            val itemLine = if(itemPositionForGroup % spanCount ==0) itemPositionForGroup / spanCount else (itemPositionForGroup / spanCount)+1
            lineCount == itemLine
        }
    }

    fun refresh(followInfoList: List<FollowInfo>) {
        followList = followInfoList.toMutableList()
        rebuildData()
        notifyDataSetChanged()
    }

    private class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivComicPic: ImageView = itemView.findViewById(R.id.iv_comic_pic)
        val tvComicName: TextView = itemView.findViewById(R.id.tv_comic_name)
        val tvComicUpdate: TextView = itemView.findViewById(R.id.tv_comic_update)
        val tvComicMark: TextView = itemView.findViewById(R.id.tv_comic_history)
        companion object {
            const val layoutId = R.layout.list_item_follow
            const val type: Int = 3
        }
    }

    private class EmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivEmptyIcon: ImageView = itemView.findViewById(R.id.iv_empty_follow_tips)
        companion object {
            const val layoutId = R.layout.list_item_follow_empty
            const val type: Int = 2
        }
    }

    class GroupHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGroupTitle: TextView = itemView.findViewById(R.id.tv_group_title)
        companion object {
            const val layoutId = R.layout.list_item_follow_group
            const val type: Int = 1
        }
    }
}

