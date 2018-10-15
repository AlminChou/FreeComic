package com.almin.freecomic.module.common.datasource.network.response

import com.almin.freecomic.module.common.datasource.model.UserProfile
 data class LoginResponse(
    val data: UserProfile,
    val result: Int,
    val msg: String){


     companion object {
         const val SUCCESS = 1
         const val FAILED = 0
     }

 }
