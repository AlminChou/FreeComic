package com.almin.library.permission

/**
 * Created by Almin on 2018/7/6.
 */
interface PermissionGrantListener{
    fun onPermissionGrantSuccess()
    fun onPermissionGrantFailed()
}