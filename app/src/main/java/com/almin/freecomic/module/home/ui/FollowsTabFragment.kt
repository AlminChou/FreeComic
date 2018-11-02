package com.almin.freecomic.module.home.ui

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import com.almin.freecomic.module.home.adapter.FollowListAdapter
import com.almin.freecomic.module.home.contract.FollowsContract
import com.almin.freecomic.widget.PinHeaderDecoration
import com.almin.library.imageloader.ImageLoader
import kotlinx.android.synthetic.main.fragment_tab_follows.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Almin on 2018/10/18.
 */

/**
 * refactor mark : need to use diff util to refresh recycleView (pending)
 */
class FollowsTabFragment  : AbstractTabFragment(),FollowsContract.ViewRenderer{

    private val imageLoader: ImageLoader by inject()
    private var adapter: FollowListAdapter? = null

    init{
        autoAttachToolbar = true
        layoutResourceId = R.layout.fragment_tab_follows
        pageTitle = "我的订阅"
    }

    override val presenter: FollowsContract.Presenter by inject{ parametersOf(this,this) }


    override fun initView(view: View) {
        super.initView(view)
        val layoutManager = GridLayoutManager(activity,3)
        layoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val adapter = rv_follows.adapter as FollowListAdapter
                return if(adapter.isGroupPosition(position)) 3 else 1
            }
        }
        rv_follows.layoutManager = layoutManager
        swipe_refresh.setOnRefreshListener {
            presenter.start(null)
        }

        btn_retry_fetch.setOnClickListener {
            swipe_refresh.isRefreshing = true
            presenter.start(null)
        }
    }

    override fun initData() {
        btn_retry_fetch.performClick()
    }

    override fun displayFollowList(followInfoList: List<FollowInfo>) {
        if(adapter==null){
            adapter = FollowListAdapter(imageLoader,followInfoList.toMutableList())
            rv_follows.addItemDecoration(PinHeaderDecoration(adapter!!))
            rv_follows.adapter = adapter
        }else{
            adapter!!.refresh(followInfoList)
        }
        if(swipe_refresh.isRefreshing){
            swipe_refresh.isRefreshing = false
        }
    }

    override fun onFetchFollowListError() {
        showToast("获取失败，请稍后重试")
        if(swipe_refresh.isRefreshing){
            swipe_refresh.isRefreshing = false
        }
    }

    override fun enableRetryButton() {
        btn_retry_fetch.visibility = View.VISIBLE
    }


    override fun navigateToComicDetailPage(comicId: String) {
    }

    override fun reload() {
        btn_retry_fetch?.performClick()
    }


}