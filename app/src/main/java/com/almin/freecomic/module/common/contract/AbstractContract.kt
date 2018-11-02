package com.almin.freecomic.module.common.contract

interface AbstractContract {

    interface ViewRenderer {
        fun showSpinner()  // 显示loading 进度旋转条
        fun hideSpinner()
        fun goBack()
        fun showToast(msg: String)
        fun showSnackBar(msg: String)
    }

    interface Presenter<T> {
        fun start(t: T?)   //做页面第一次初始化动作的所有内容
        fun detach()  //销毁一些presenter里面用到的list数据集合等缓存

        companion object {

            // for simple ui not need presenter
            val EMPTY: Presenter<Any?> = object : Presenter<Any?> {
                override fun start(t: Any?) {}

                override fun detach() {}
            }
        }
    }


    interface DataSource {
        //   saveToRP /getFromRP (Repository: DateBase, Manager)
        //   saveToSP /getFromSP (SharedPreferences)
    }

}
