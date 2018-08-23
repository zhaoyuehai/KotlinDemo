package com.cnegroup.kotlindemo.base

import android.support.v4.app.Fragment
import android.util.Log

/**
 * Fragment基类，无关MVP
 * Created by zhaoyuehai 2018/8/1
 */
class BaseFragment : Fragment() {
    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}