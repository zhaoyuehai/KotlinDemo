package com.yuehai.contract

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface MainContract {

    interface IView : com.yuehai.base.IBaseView {
    }

    interface IPresenter : com.yuehai.base.IBasePresenter<IView> {
    }
}