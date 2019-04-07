package com.almin.freecomic.module.comic.presenter

import android.os.Bundle
import com.almin.freecomic.module.comic.contract.Intent
import com.almin.freecomic.module.comic.contract.ComicInfoContract
import com.almin.freecomic.module.comic.datasource.model.ComicInfo
import com.almin.freecomic.module.comic.datasource.model.response.ComicInfoRsp
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/11/14.
 */
class ComicInfoPresenter(private val viewRenderer: ComicInfoContract.ViewRenderer,
                         private val dataSource: ComicInfoContract.DataSource) : ComicInfoContract.Presenter{

    private lateinit var comicId: String
    private lateinit var comicAvatarUrl: String


    override fun start(arguments: Bundle) {
        comicId = arguments.getString(Intent.BUNDLE_KEY_COMIC_ID)
        comicAvatarUrl = arguments.getString(Intent.BUNDLE_KEY_COMIC_AVATAR_URL)
    }

    override fun loadComicInfo() {
        dataSource.getComicInfo(comicId, object : HttpResultSubscriber<ComicInfo>() {
            override fun onSuccess(t: ComicInfo) {
                viewRenderer.displayComicInfo(t)
            }
        })
    }

    override fun clickRead() {
        viewRenderer.navigateToReadComicPage()
    }


    override fun detach() {

    }


}