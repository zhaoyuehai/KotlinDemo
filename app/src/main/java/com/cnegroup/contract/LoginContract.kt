package com.cnegroup.contract

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

}