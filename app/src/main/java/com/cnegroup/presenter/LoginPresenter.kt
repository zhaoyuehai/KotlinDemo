package com.cnegroup.presenter

import android.text.TextUtils
import com.cnegroup.MyApplication
import com.cnegroup.base.BasePresenter
import com.cnegroup.contract.LoginContract
import com.cnegroup.data.bean.ResultBean
import com.cnegroup.data.bean.UserBean
import com.cnegroup.data.net.ResultObserver
import com.cnegroup.model.LoginModel
import io.reactivex.disposables.Disposable

/**
 * 登录
 * Created by zhaoyuehai 2018/8/1
 */
class LoginPresenter : BasePresenter<LoginContract.IView>(), LoginContract.IPresenter {

    private var loginModule: LoginModel = LoginModel()

    override fun login(username: String, password: String) {
        if (getView() == null) return
        if (checkInput(username, password)) {
            loginModule.login(username, password, object : ResultObserver<ResultBean<UserBean>>(this, true) {

                override fun onNext(result: ResultBean<UserBean>) {
                    if (getView() == null) return
                    getView()!!.showToast(result.message)
                    if (result.code == "10000") {
                        MyApplication.mContext.setUser(result.data)
                    }
                }

                override fun onComplete() {
                    super.onComplete()
                    if (getView() != null) {
                        getView()!!.showProgress(false)
                        if (MyApplication.mContext.getUser() != null)
                            getView()!!.goMain()
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    super.onSubscribe(d)
                    if (getView() != null)
                        getView()!!.showProgress(true)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    if (getView() != null) {
                        getView()!!.showProgress(false)
                    }
                }

            })
        }
    }


    //检查输入
    private fun checkInput(username: String, password: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            getView()!!.onUserNameInputError("请输入用户名或手机号")
            return false
        }
        if (username.isEmpty() || username.length > 16) {
            getView()!!.onUserNameInputError("输入格式错误")
            return false
        }
        if (TextUtils.isEmpty(password)) {
            getView()!!.onPassWordInputError("请输入密码")
            return false
        }
        if (password.length < 4 || username.length > 16) {
            getView()!!.onPassWordInputError("密码格式错误")
            return false
        }
        return true
    }


}