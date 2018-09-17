package com.cnegroup.contract

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface MainContract {

    interface IView : com.cnegroup.base.IView {
    }

    interface IPresenter : com.cnegroup.base.IPresenter<IView> {
    }
}