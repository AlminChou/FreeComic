package com.almin.library.manager

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*

class ActivityBackStackManager private constructor() {

    private val activityBackStacks = LinkedList<BackStack>()

    fun register(activity: Activity) {
        val backStack = BackStack()
        backStack.weakReference = WeakReference(activity)
        if (!activityBackStacks.contains(backStack)) {
            activityBackStacks.addFirst(backStack)
        }
    }

    fun unRegister(activity: Activity) {
        val backStack = BackStack()
        backStack.weakReference = WeakReference(activity)
        activityBackStacks.remove(backStack)
    }

    class BackStack {
        var weakReference: WeakReference<out Activity>? = null

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false

            val backStack = o as BackStack?

            if (weakReference != null && backStack!!.weakReference != null) {
                val activity = weakReference!!.get()
                if (activity != null) {
                    return activity == backStack.weakReference!!.get()
                }
            }

            return false
        }

        override fun hashCode(): Int {
            if (weakReference != null) {
                val activity = weakReference!!.get()
                if (activity != null) {
                    return activity.hashCode()
                }
            }

            return 0
        }
    }

    companion object {
        val instance = ActivityBackStackManager()
    }
}
