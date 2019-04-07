package com.almin.freecomic.module.comic.datasource.model.response

data class Data(
        val chapter_id: Int,
        val chapter_order: Int,
        val chapter_title: String,
        val filesize: Int,
        val updatetime: Int
)