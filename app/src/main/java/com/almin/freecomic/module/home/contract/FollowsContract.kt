package com.almin.freecomic.module.home.contract

import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/10/18.
 */
interface FollowsContract{

    interface ViewRenderer : AbstractContract.ViewRenderer{
        fun displayFollowList()
        fun navigateToComicDetailPage(comicId: String)
    }

    interface Presenter : AbstractContract.Presenter<Unit>{

    }

    interface DataSource : AbstractContract.DataSource{
        fun getFollowList(userId: String, httpResultSubscriber: HttpResultSubscriber<String>)
    }
}