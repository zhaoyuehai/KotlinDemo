package com.yuehai.contract

import com.yuehai.data.bean.ResultBean
import com.yuehai.data.bean.UserBean
import com.yuehai.data.net.ResultObserver

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface LoginContract {

    interface IView : com.yuehai.base.IBaseView {

        fun showProgress(show: Boolean)

        fun onUserNameInputError(s: String)

        fun onPassWordInputError(s: String)

        fun finish()
    }

    interface IPresenter : com.yuehai.base.IBasePresenter<IView> {
        /**
         * 登录
         */
        fun login(username: String, password: String)
    }

    interface IModel {
        /**
         * 登录
         */
        fun login(username: String, password: String, observer: ResultObserver<ResultBean<UserBean>>)
    }
}