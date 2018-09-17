package com.cnegroup.base

import io.reactivex.internal.disposables.DisposableContainer

/**
 * MVP中的Presenter基类接口
 * Created by zhaoyuehai 2018/9/14
 */
interface IBasePresenter<T> : DisposableContainer {

    fun attachView(view: T)

    fun detachView()

    fun getView(): T?
}