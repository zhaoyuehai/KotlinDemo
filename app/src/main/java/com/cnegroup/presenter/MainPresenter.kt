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
    }

    fun onLoginClick() {
        if (MyApplication.mContext.getUser() != null) {
            MyApplication.mContext.setUser(null)
        } else {
        }
    }

    fun onSettingClick() {
        if (getView() == null) return
        showToast("Setting被点击")
    }

}