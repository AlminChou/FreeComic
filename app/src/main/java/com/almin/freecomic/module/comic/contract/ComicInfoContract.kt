package com.almin.freecomic.module.comic.contract

import com.almin.freecomic.module.comic.datasource.model.ComicInfo
import com.almin.freecomic.module.comic.datasource.model.response.ComicInfoRsp
import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/11/2.
 */
interface ComicInfoContract{

    interface ViewRenderer: AbstractContract.ViewRenderer{
        fun displayComicInfo(info: ComicInfo)
        fun navigateToReadComicPage()
    }

    interface Presenter: AbstractContract.Presenter{
        fun loadComicInfo()
        fun clickRead()
    }

    interface DataSource: AbstractContract.DataSource{
        fun getComicInfo(comicId: String, httpResultSubscriber: HttpResultSubscriber<ComicInfo>)
    }
}