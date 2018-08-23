package com.cnegroup.kotlindemo.contract

import com.cnegroup.kotlindemo.base.BaseResultBean
import com.cnegroup.kotlindemo.base.IBaseView
import com.cnegroup.kotlindemo.data.net.BaseObserver
import com.cnegroup.kotlindemo.data.response.UserBean

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface LoginContract {

    interface ILoginView : IBaseView {

        fun showProgress(show: Boolean)

        fun onUserNameInputError(s: String)

        fun onPassWordInputError(s: String)

        fun goMain()
    }

}