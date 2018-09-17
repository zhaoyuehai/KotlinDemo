package com.cnegroup.data.net

import com.cnegroup.util.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by zhaoyuehai 2018/8/2
 */
abstract class ResultObserver<T> : Observer<T> {

    private var disposable: Disposable? = null
    private var container: DisposableContainer? = null
    private var showErrorToast: Boolean//请求异常时  是否弹土司警告

    constructor(showErrorToast: Boolean) {
        this.showErrorToast = showErrorToast
    }

    constructor(container: DisposableContainer?, showErrorToast: Boolean) {
        this.container = container
        this.showErrorToast = showErrorToast
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
        if (container != null) {
            container!!.add(disposable)
        }
    }

    override fun onError(e: Throwable) {
        if (showErrorToast) {
            val message: String
            if (e is ConnectException) {//手机没连上网的情况
                message = "网络异常，请检查网络后重试！"
            } else if (e is HttpException) {//404 500 服务器连接错误
                message = "服务器连接异常！"
            } else if (e is SocketTimeoutException) {//连上网，连接超时无响应
                message = "对不起，连接超时！"
            } else {
                message = "请求失败"
            }
            ToastUtils.showToast(message)
        }
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