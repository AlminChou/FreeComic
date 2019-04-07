package com.almin.freecomic.module.comic.datasource.model.response

data class LatestComment(
        val avatar: String,
        val comment_id: Int,
        val content: String,
        val createtime: Int,
        val nickname: String,
        val uid: Int
)