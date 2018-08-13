package com.cnegroup.kotlindemo.presenter

import com.cnegroup.kotlindemo.MyApplication
import com.cnegroup.kotlindemo.base.BaseApplication
import com.cnegroup.kotlindemo.base.BasePresenter
import com.cnegroup.kotlindemo.contract.MainContract
import javax.inject.Inject

/**
 * Created by zhaoyuehai 2018/8/1
 */
class MainPresenter @Inject constructor() : BasePresenter<MainContract.IMainView>() {

    fun onSnackBarClick() {
        if (getView() == null) return
        getView()!!.showToast("SnackBar被点击")
        getView()!!.goLogin()
    }

    fun onLoginClick() {
        if (MyApplication.mContext.getUser() != null) {
            MyApplication.mContext.setUser(null)
            if (getView() != null)
                getView()!!.initData()
        } else {
            if (getView() != null)
                getView()!!.goLogin()
        }
    }

    fun onSettingClick() {
        if (getView() == null) return
        getView()!!.showToast("Setting被点击")
    }

}