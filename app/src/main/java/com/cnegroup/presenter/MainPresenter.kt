package com.cnegroup.presenter

import com.cnegroup.MyApplication
import com.cnegroup.base.BasePresenter
import com.cnegroup.contract.MainContract
import com.cnegroup.util.ToastUtils.showToast

/**
 * Created by zhaoyuehai 2018/8/1
 */
class MainPresenter : BasePresenter<MainContract.IView>() {

    fun onSnackBarClick() {
        if (getView() == null) return
        showToast("SnackBar被点击")
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
        showToast("Setting被点击")
    }

}