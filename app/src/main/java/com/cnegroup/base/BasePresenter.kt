package com.cnegroup.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * MVP中的Presenter基类
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BasePresenter<T : IBaseView> : IBasePresenter<T> {

    private var mView: T? = null

    final override fun attachView(view: T) {
        mView = view
    }

    final override fun getView(): T? {
        return mView
    }

    final override fun detachView() {
        if (mCompositeDisposable != null)
            mCompositeDisposable!!.clear()
        mView = null
    }

    private var mCompositeDisposable: CompositeDisposable? = null

    final override fun add(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

    final override fun remove(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

    final override fun delete(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

}