package com.almin.library.network.retrofitlibrary.callback


import com.almin.library.network.retrofitlibrary.errorhandlecomponent.RetrofitException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.IOException

/**
 * Created by Almin on 2017/12/12.
 */

abstract class HttpResultSubscriber<T> : Observer<T> {
    private var disposable: Disposable? = null

    val isRunning: Boolean
        get() = disposable!!.isDisposed

    abstract fun onSuccess(t: T)

    open fun onError(retrofitException: RetrofitException){}

    override fun onSubscribe(disposable: Disposable) {
        this.disposable = disposable
    }

    override fun onNext(t: T) {
        onSuccess(t)
        onFinal()
    }


    //will add handle for 401（authorization）
    final override fun onError(throwable: Throwable) {
        if (throwable is RetrofitException) {
            if (throwable.kind == RetrofitException.Kind.NOCONNECTIVITY) {
                onNoConnectivityError()
            } else {
                onError(throwable)
            }
        }
        onFinal()
    }

    /**
     * The [Observable] will not call this method if it calls [.onError].
     */
    override fun onComplete() {
        // don't use this
    }

    open fun onFinal() {}

    fun onNoConnectivityError() {
        onError(RetrofitException.noConnectivityError(IOException("当前网络不可用，请检查你的网络设置")))
    }


    fun cancel() {
        disposable!!.dispose()
    }

}
