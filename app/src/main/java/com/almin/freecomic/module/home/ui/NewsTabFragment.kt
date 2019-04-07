package com.almin.freecomic.module.home.ui

import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.module.common.contract.AbstractContract
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Almin on 2018/10/18.
 */
class NewsTabFragment : AbstractTabFragment(){

    init{
        autoAttachToolbar = true
        layoutResourceId = R.layout.fragment_tab_news
    }


    override val presenter: AbstractContract.Presenter = AbstractContract.Presenter.EMPTY




    override fun initView(view: View) {
        super.initView(view)
    }

    override fun initData() {

        Observable.just("1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .map {
                    println("  xiancheng  :    ${Thread.currentThread().name}")
                }
//                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.computation())
                .map {
                    println("  xiancheng  :    ${Thread.currentThread().name}")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    println("  xiancheng  :    ${Thread.currentThread().name}")
                })

    }


    override fun reload() {
    }

}