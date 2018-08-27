package com.cnegroup.base

import android.os.Bundle
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
    protected var mInflater: LayoutInflater? = null
    protected var mBundle: Bundle? = null
    protected var mRoot: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBundle = arguments
        initBundle(mBundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRoot != null) {
            if (mRoot!!.parent != null) {
                val parent: ViewGroup = mRoot!!.parent as ViewGroup
                parent.removeView(mRoot)
            }
        } else {
            mInflater = inflater
            mRoot = mInflater!!.inflate(getLayoutId(), container, false)
            onRestartInstance(savedInstanceState)
            initWidget(mRoot)
        }
        return mRoot
    }

    protected open fun initWidget(mRoot: View?) {}

    protected open fun onRestartInstance(savedInstanceState: Bundle?) {}

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun init()

    protected open fun initBundle(mBundle: Bundle?) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}