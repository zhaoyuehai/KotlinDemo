package com.cnegroup.contract

import com.cnegroup.data.bean.ResultBean
import com.cnegroup.data.bean.UserBean
import com.cnegroup.data.net.ResultObserver

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface LoginContract {

    interface IView : com.cnegroup.base.IBaseView {

        fun showProgress(show: Boolean)

        fun onUserNameInputError(s: String)

        fun onPassWordInputError(s: String)

        fun finish()
    }

    interface IPresenter : com.cnegroup.base.IBasePresenter<IView> {
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