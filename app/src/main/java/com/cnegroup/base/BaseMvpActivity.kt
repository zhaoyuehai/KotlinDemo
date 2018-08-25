package com.cnegroup.base

import android.os.Bundle
import android.support.annotation.LayoutRes

/**
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : SwipeBackActivity(), IBaseView {

    //P
    lateinit var mPresenter: T

    //必须是layout布局文件id
    @LayoutRes
    abstract fun getContentViewId(): Int

    abstract fun getPresenter(): T

    abstract fun init()

    abstract fun attachView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        mPresenter = getPresenter()
        attachView()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}