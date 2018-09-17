package com.cnegroup.base

import android.support.annotation.StringRes

/**
 * MVP中的View基类接口
 * Created by zhaoyuehai 2018/8/1
 */
interface IView {

    fun showToast(msg: String)

    fun showToast(@StringRes msgId: Int)

}