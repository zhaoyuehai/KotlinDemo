package com.cnegroup.base

import android.view.View
import com.cnegroup.util.ToastUtils

/**
 * Fragment MVP基类
 * Created by zhaoyuehai 2018/9/14
 */
abstract class BaseMvpFragment<T : IBasePresenter<*>> : BaseFragment(), IBaseView {

    //P
    lateinit var mPresenter: T

    /**
     * 创建返回Presenter 并且 attachView
     * example:
     *
    override fun initPresenter(): CenterContract.IBasePresenter = with(CenterPresenter()) {
    attachView(this@CenterFragment)
    return this
    }
     *
     */
    abstract fun initPresenter(): T

    override fun initView(mRoot: View?) {
        super.initView(mRoot)
        mPresenter = initPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showToast(msg: String) {
        ToastUtils.showToast(msg)
    }

    override fun showToast(msgId: Int) {
        ToastUtils.showToast(msgId)
    }
}