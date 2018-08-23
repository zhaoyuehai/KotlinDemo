package com.cnegroup.kotlindemo.data.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * Created by zhaoyuehai 2018/8/2
 */
abstract class BaseObserver<T> : Observer<T> {

    private var disposable: Disposable? = null
    private var container: DisposableContainer? = null

    constructor()

    constructor(container: DisposableContainer?) {
        this.container = container
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
        if (container != null) {
            container!!.add(disposable)
        }
    }

    override fun onError(e: Throwable) {
        if (disposable != null) {
            if (!disposable!!.isDisposed) {
                disposable!!.dispose()
            }
            if (container != null) {
                container!!.delete(disposable)
                container = null
            }
        }
    }

    override fun onComplete() {
        if (disposable != null) {
            if (!disposable!!.isDisposed) {
                disposable!!.dispose()
            }
            if (container != null) {
                container!!.delete(disposable)
                container = null
            }
        }
    }
}