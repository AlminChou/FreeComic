package com.almin.freecomic.module.common.datasource.model

/**
 * Created by Almin on 2018/6/22.
 */

data class UserProfile(
    val uid: String,
    val nickname: String,
    val dmzj_token: String,
    val photo: String,
    val bind_phone: String,
    val email: String,
    val passwd: String
)