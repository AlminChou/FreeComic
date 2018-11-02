package com.almin.freecomic.module.common.datasource.model.response


data class FollowInfo(
    val name: String,
    val sub_update: String,
    val sub_img: String,
    val sub_uptime: Int,
    val sub_first_letter: String,
    val sub_readed: Int,   //   0  未读  1 已读最新
    val id: Int,
    val status: String    // 完结 连载中
){

    constructor(name: String) : this(name,"","",0,"",0,0,"")

    companion object {
        const val STATE_READED = 1
        const val STATE_UNREAD = 0
    }
}