package com.almin.freecomic.module.common.datasource.local

import com.almin.freecomic.module.common.datasource.model.UserProfile
import com.almin.freecomic.module.common.datasource.repository.UserRepository
import com.almin.library.network.retrofitlibrary.TokenProvider

/**
 * Created by Almin on 2018/6/22.
 */
class UserManager: UserRepository, TokenProvider {
    private var userProfile: UserProfile? = null

    override fun getUserProfile(): UserProfile? = userProfile

    override fun updateUserProfile(userProfile: UserProfile) {
        this.userProfile = userProfile
        println("  userProfile   ${userProfile.dmzj_token} ")
    }
    override fun getToken(): String? = userProfile?.dmzj_token
}