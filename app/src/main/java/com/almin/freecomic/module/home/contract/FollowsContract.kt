package com.almin.freecomic.module.home.contract

import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber

/**
 * Created by Almin on 2018/10/18.
 */
interface FollowsContract{

    interface ViewRenderer : AbstractContract.ViewRenderer{
        fun displayFollowList(followInfoList: List<FollowInfo>)
        fun navigateToComicDetailPage(comicId: String)
        fun onFetchFollowListError()
        fun enableRetryButton()
    }

    interface Presenter : AbstractContract.Presenter<Any?>{

    }

    interface DataSource : AbstractContract.DataSource{
        fun getFollowList(httpResultSubscriber: HttpResultSubscriber<List<FollowInfo>>)
    }
}