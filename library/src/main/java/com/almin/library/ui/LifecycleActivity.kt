package com.almin.library.ui

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.almin.library.manager.ActivityBackStackManager
import com.almin.library.scheduler.RxSchedulerHelper
import com.almin.library.scheduler.SchedulerProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.*

/**
 * Created by Almin on 2017/12/12.
 */

abstract class LifecycleActivity : AppCompatActivity(), SchedulerProvider {

    private val mLifecycleSubject = PublishSubject.create<ActivityEvent>()
    private val mDisposables = ArrayList<Disposable>()


    override fun lifecycle(): Observable<ActivityEvent> {
        return mLifecycleSubject.hide()
    }

    override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(mLifecycleSubject, event)
    }

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityBackStackManager.instance.register(this)
    }

    @CallSuper
    override fun onDestroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY)
        ActivityBackStackManager.instance.unRegister(this)
        unSubscribeSubjects()
        super.onDestroy()
    }

    @CallSuper
    override fun onStop() {
        mLifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    override fun <T> asyncUIScheduler(): ObservableTransformer<T, T> = ObservableTransformer {
        upstream ->
            upstream.compose(RxSchedulerHelper.io_main())
                    .compose(bindUntilEvent(ActivityEvent.DESTROY))
    }

    override fun <T> destroyScheduler(): ObservableTransformer<T, T> = bindUntilEvent(ActivityEvent.DESTROY)

    protected fun subscribeSubject(disposable: Disposable) {
        if (!mDisposables.contains(disposable)) {
            mDisposables.add(disposable)
        }
    }

    private fun unSubscribeSubjects() {
        for (disposable in mDisposables) {
            disposable.dispose()
        }
        mDisposables.clear()
    }
}
