package com.cnegroup.kotlindemo.contract

import com.cnegroup.kotlindemo.base.IBaseView

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface MainContract {

    interface IMainView : IBaseView {
        fun goLogin()
        fun initData()
    }

}