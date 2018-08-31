package com.almin.library


import com.almin.library.network.retrofitlibrary.ApiConfigProvider
import com.almin.library.network.retrofitlibrary.RetrofitConfiguration

/**
 * Created by Almin on 2018/5/16.
 */

abstract class AbstractConfiguration : RetrofitConfiguration, ApiConfigProvider{
    abstract fun isDebugBuild(): Boolean

    abstract fun isReleaseBuild(): Boolean
}
