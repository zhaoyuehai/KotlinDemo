package com.cnegroup.data.net

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhaoyuehai 2018/8/2
 */
class RxSchedulers {
    companion object {
        fun <T> io_main(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                upstream ->
                return@ObservableTransformer upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}