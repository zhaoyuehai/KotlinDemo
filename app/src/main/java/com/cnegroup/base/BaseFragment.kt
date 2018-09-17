package com.cnegroup.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragment基类，无关MVP
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mInflater: LayoutInflater
    protected var mBundle: Bundle? = null
    protected var mRoot: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBundle = arguments
        initBundle(mBundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mInflater = inflater
        if (mRoot != null) {
            if (mRoot!!.parent != null) {
                val parent: ViewGroup = mRoot!!.parent as ViewGroup
                parent.removeView(mRoot)
            }
        } else {
            mRoot = mInflater.inflate(getLayoutId(), container, false)
            onRestartInstance(savedInstanceState)
            initView(mRoot)
            initData()
        }
        return mRoot
    }

    protected open fun initView(mRoot: View?) {}

    protected open fun initData() {}

    protected open fun onRestartInstance(savedInstanceState: Bundle?) {}

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected fun <T : View?> findViewById(@IdRes resourceIdRes: Int): T {
        return mRoot!!.findViewById<T>(resourceIdRes)
    }

    protected open fun initBundle(mBundle: Bundle?) {}

    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}