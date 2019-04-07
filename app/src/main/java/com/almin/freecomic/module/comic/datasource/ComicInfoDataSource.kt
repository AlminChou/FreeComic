package com.almin.freecomic.module.comic.datasource

import com.almin.freecomic.module.comic.contract.ComicInfoContract
import com.almin.freecomic.module.comic.datasource.model.ComicInfo
import com.almin.freecomic.module.comic.datasource.model.response.ComicInfoRsp
import com.almin.freecomic.module.common.datasource.apiservice.ComicApiService
import com.almin.library.network.retrofitlibrary.callback.HttpResultSubscriber
import com.almin.library.scheduler.SchedulerProvider

/**
 * Created by Almin on 2018/11/14.
 */
class ComicInfoDataSource(private val schedulerProvider: SchedulerProvider,
                          private val comicApiService: ComicApiService) : ComicInfoContract.DataSource{


    override fun getComicInfo(comicId: String, httpResultSubscriber: HttpResultSubscriber<ComicInfo>) {
        comicApiService.getComicInfo(comicId)
                .map { comicInfoRsp ->
                    val comicInfo = ComicInfo()
                    comicInfo.title = comicInfoRsp.title
                    comicInfo.avatar = comicInfoRsp.cover
                    comicInfo.authors = comicInfoRsp.authors.map { it.tag_name }.joinToString { " " }
                    comicInfo
                }
                .compose(schedulerProvider.asyncUIScheduler())
                .subscribe(httpResultSubscriber)
    }


}