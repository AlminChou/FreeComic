package com.almin.freecomic.module.comic.datasource.model

/**
 * Created by Almin on 2018/12/5.
 */
class ComicInfo{

    lateinit var title: String
    lateinit var authors: String
    lateinit var typeFlags: String
    lateinit var lastUpdateStatus: String
    var lastUpdateTime: Long = 0
    lateinit var avatar: String

}