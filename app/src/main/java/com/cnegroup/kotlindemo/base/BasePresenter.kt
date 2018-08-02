package com.cnegroup.kotlindemo.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * MVP中的Presenter基类
 * Created by zhaoyuehai 2018/8/1
 *
 * 注意：Presenter因为需要注入到Activity中，所以需要 是open的； 并且构造方法需要 @Inject修饰
 *
 * 例如：open class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.ILoginView>()......
 */
open class BasePresenter<T : IBaseView> {

    fun getView(): T? {
        return mView
    }

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mView: T? = null

    fun attachView(view: T) {
        mView = view
    }

    fun detachView() {
        mView = null
        if (mCompositeDisposable != null)
            mCompositeDisposable!!.clear()
    }

    fun addDisposable(disposable: Disposable?) {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        if (disposable != null)
            mCompositeDisposable!!.add(disposable)
    }

    fun deleteDisposable(disposable: Disposable?) {
        if (disposable != null && mCompositeDisposable != null)
            mCompositeDisposable!!.delete(disposable)
    }

}