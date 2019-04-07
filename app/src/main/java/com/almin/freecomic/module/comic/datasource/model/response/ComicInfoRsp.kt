package com.almin.freecomic.module.comic.datasource.model.response

data class ComicInfoRsp(
        val authors: List<Author>,
        val chapters: List<Chapter>,
        val comment: Comment,
        val copyright: Int,
        val cover: String,
        val description: String,
        val direction: Int,
        val first_letter: String,
        val hit_num: Int,
        val hot_num: Int,
        val id: Int,
        val is_dmzj: Int,
        val islong: Int,
        var last_updatetime: Long,
        val status: List<Statu>,
        val subscribe_num: Int,
        val title: String,
        val types: List<Type>,
        val uid: Any
){

    init {
        last_updatetime *= 1000
    }
}