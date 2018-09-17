package com.cnegroup.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.cnegroup.util.ToastUtils

/**
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BaseMvpActivity<T : IBasePresenter<*>> : SwipeBackActivity() {

    //P
    lateinit var mPresenter: T

    //必须是layout布局文件id
    @LayoutRes
    abstract fun getContentViewId(): Int

    /**
     * 创建返回Presenter 并且 attachView
     *  example:
     *
    override fun initPresenter(): MainContract.IPresenter = with(MainPresenter()) {
    attachView(this@MainActivity)
    return this
    }
     *
     */
    abstract fun initPresenter(): T

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        mPresenter = initPresenter()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    fun showToast(msg: String) {
        ToastUtils.showToast(msg)
    }

    fun showToast(msgId: Int) {
        ToastUtils.showToast(msgId)
    }
}