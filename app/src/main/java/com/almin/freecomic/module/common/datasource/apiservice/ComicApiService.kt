package com.almin.freecomic.module.common.datasource.apiservice

import com.almin.freecomic.module.common.datasource.model.response.FollowInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Almin on 2018/10/18.
 */
interface ComicApiService{


    //获取  我的订阅信息
    // URL	https://v3api.dmzj.com/UCenter/subscribe?uid=102018822&sub_type=1&letter=all
    //       &dmzj_token=97de3098ebda29311386940a372c34d3&page=0&type=0&channel=Android&version=2.7.009
    // sub_type: 1 全部  2 未读 3 已读 4 完结

    @GET("/v3_api_key/UCenter/subscribe")
    fun getPersonalSubscription(@Query("uid") usedId: String) : Observable<List<FollowInfo>>

}