package com.almin.freecomic.module.common.datasource.repository

import com.almin.freecomic.module.common.datasource.model.UserProfile

/**
 * Created by Almin on 2018/10/22.
 */
interface UserRepository{
    fun getUserProfile(): UserProfile?
    fun updateUserProfile(userProfile: UserProfile)
}