package com.almin.freecomic.manager

import com.almin.freecomic.mvp.datasource.model.UserProfile

/**
 * Created by Almin on 2018/6/22.
 */
class UserManager{

    companion object {
        private val instance = UserManager()
        fun instance() = instance
    }

    var userProfile : UserProfile ? = null



    fun loadCacheFromDB(){

    }

}