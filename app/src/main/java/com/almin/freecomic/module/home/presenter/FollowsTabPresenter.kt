package com.almin.freecomic.module.home.presenter

import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import com.almin.freecomic.module.home.contract.FollowsContract
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.network.retrofitlibrary.errorhandlecomponent.RetrofitException

/**
 * Created by Almin on 2018/10/18.
 */
class FollowsTabPresenter(private val viewRenderer: FollowsContract.ViewRenderer,
                          private val dataSource: FollowsContract.DataSource): FollowsContract.Presenter{
    private var followInfoList: List<FollowInfo>? = null

    override fun start(t: Any?) {
        dataSource.getFollowList(object : HttpResultSubscriber<List<FollowInfo>>() {
            override fun onSuccess(t: List<FollowInfo>) {
                followInfoList = t
                viewRenderer.displayFollowList(t)
            }

            override fun onError(retrofitException: RetrofitException) {
                followInfoList?.isEmpty().let { viewRenderer.enableRetryButton() }
                viewRenderer.onFetchFollowListError()
            }
        })
    }

    override fun detach() {
        followInfoList!!.toMutableList().clear()
    }

}