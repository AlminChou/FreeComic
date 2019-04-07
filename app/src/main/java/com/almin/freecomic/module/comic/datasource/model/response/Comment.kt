package com.almin.freecomic.module.comic.datasource.model.response

data class Comment(
        val comment_count: Int,
        val latest_comment: List<LatestComment>
)