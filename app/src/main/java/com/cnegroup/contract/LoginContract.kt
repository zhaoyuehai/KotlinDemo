package com.cnegroup.contract

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface LoginContract {

    interface IView : com.cnegroup.base.IView {

        fun showProgress(show: Boolean)

        fun onUserNameInputError(s: String)

        fun onPassWordInputError(s: String)

        fun finish()
    }
    interface IPresenter : com.cnegroup.base.IPresenter<IView> {
        /**
         * 登录
         */
        fun login(username: String, password: String)
    }
}