package com.almin.freecomic.module.common.datasource.network.apiservice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Almin on 2018/10/18.
 */
interface ComicApiService{


    //获取  我的订阅信息
    @GET("/interface_api_key/api/getReInfo/comic/{userId}/0")
    fun getPersonalSubscription(@Path("userId") usedId: String) : Observable<String>

}