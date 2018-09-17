package com.cnegroup.contract

import com.cnegroup.base.IBasePresenter
import com.cnegroup.base.IBaseView

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface LoginContract {

    interface IView : IBaseView {

        fun showProgress(show: Boolean)

        fun onUserNameInputError(s: String)

        fun onPassWordInputError(s: String)

        fun goMain()
    }
    interface IPresenter : IBasePresenter<IView> {
        /**
         * 登录
         */
        fun login(username: String, password: String)
    }
}