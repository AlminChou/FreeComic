package com.almin.library.scheduler

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.ObservableTransformer

/**
 * Created by Almin on 2018/8/18.
 * provide a safe scheduler about destroy lifecycle & async io to ui thread
 */
interface SchedulerProvider : LifecycleProvider<ActivityEvent> {

    // a safe scheduler for cancel task when ui is destroy
    fun <T> destroyScheduler(): ObservableTransformer<T, T>

    // a safe scheduler for api request or async data update when ui is destroy
    fun <T> asyncUIScheduler(): ObservableTransformer<T, T>
}