package com.almin.freecomic.module.common.datasource.apiservice

import com.almin.freecomic.module.comic.datasource.model.response.ComicInfoRsp
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


    //   step 1 find base info
    @GET("/v3_api_key/comic/{comicId}.json")
    fun getComicInfo(@Path("comicId") comicId: String) : Observable<ComicInfoRsp>

    //  step 2 find history    (get  chapter info id   )
    //   URL https://interface.dmzj.com/api/getReInfo/comic/102018822/9949/0
    @GET("/interface_api_key/api/getReInfo/comic/{userId}/{comicid}/0")
    fun getReadInfo(@Path("userId") userId: String, @Path("comicId") comicId: String) : Observable<String>

    //  step 3 find page
    //https://v3api.dmzj.com/chapter/9949/78442.json
    @GET("/v3_api_key/chapter/{comicId}/{chapterId}.json")
    fun getLastReadPage(@Path("comicId") comicId: String, @Path("chapterId") chapterId: String) : Observable<String>
}