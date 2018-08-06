package com.cnegroup.kotlindemo

import android.view.View
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.cnegroup.kotlindemo.base.BaseApplication

/**
 * Created by zhaoyuehai 2018/8/1
 */
class MyApplication : BaseApplication() {

    val problemViewClassList: List<Class<View>> = ArrayList()

    override fun onCreate() {
        super.onCreate()
        BGASwipeBackHelper.init(this, problemViewClassList)
    }
}