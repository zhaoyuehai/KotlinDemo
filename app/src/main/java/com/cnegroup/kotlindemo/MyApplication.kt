package com.cnegroup.kotlindemo

import android.view.View
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.cnegroup.kotlindemo.base.BaseApplication

/**
 * Created by zhaoyuehai 2018/8/1
 */
class MyApplication : BaseApplication() {

    //静态的
    companion object {
        lateinit var mContext: MyApplication
    }

    val problemViewClassList: List<Class<View>> = ArrayList()

    override fun onCreate() {
        mContext = this
        super.onCreate()
        BGASwipeBackHelper.init(this, problemViewClassList)
    }
}