package com.cnegroup.kotlindemo.data.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by zhaoyuehai 2018/8/2
 */
abstract class BaseObserver<T> : Observer<T> {
    private var disposable: Disposable? = null

    final override fun onSubscribe(d: Disposable) {
        disposable = d
        onStart(disposable)
    }

    final override fun onError(e: Throwable) {
        if (disposable != null) {
            if (!disposable!!.isDisposed) {
                disposable!!.dispose()
            }
        }
        onFinish(disposable, e)
    }

    final override fun onComplete() {
        if (disposable != null) {
            if (!disposable!!.isDisposed) {
                disposable!!.dispose()
            }
        }
        onFinish(disposable, null)
    }

    abstract fun onFinish(disposable: Disposable?, e: Throwable?)

    abstract fun onStart(disposable: Disposable?)
}