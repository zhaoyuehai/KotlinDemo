package com.cnegroup.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * MVP中的Presenter基类
 * Created by zhaoyuehai 2018/8/1
 *
 * 注意：Presenter因为需要注入到Activity中，所以需要 是open的； 并且构造方法需要 @Inject修饰
 *
 * 例如：open class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.ILoginView>()......
 */
abstract class BasePresenter<T : IBaseView> : DisposableContainer {

    override fun add(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

    override fun remove(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

    override fun delete(d: Disposable): Boolean {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!.add(d)
    }

    fun getView(): T? {
        return mView
    }

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mView: T? = null

    fun attachView(view: T) {
        mView = view
    }

    fun detachView() {
        if (mCompositeDisposable != null)
            mCompositeDisposable!!.clear()
        mView = null
    }

}