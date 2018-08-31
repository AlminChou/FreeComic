package com.almin.freecomic.mvp.contract

interface AbstractContract {

    interface ViewRenderer {
        fun showSpinner()  // 显示loading 进度旋转条
        fun hideSpinner()
        fun goBack()
        fun showToast(msg: String)
        fun showSnackBar(msg: String)
    }

    interface Presenter {
        fun start()   //做页面第一次初始化动作的所有内容
        fun detach()  //销毁一些presenter里面用到的list数据集合等缓存

        companion object {

            // for simple ui not need presenter
            val EMPTY: Presenter = object : Presenter {
                override fun start() {}

                override fun detach() {}
            }
        }
    }


    interface DataSource {
        //   saveToRP /getFromRP (Repository: DateBase, Manager)
        //   saveToSP /getFromSP (SharedPreferences)
    }

}
