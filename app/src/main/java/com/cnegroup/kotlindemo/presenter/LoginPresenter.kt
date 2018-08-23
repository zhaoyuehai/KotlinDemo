package com.cnegroup.kotlindemo.presenter

import android.text.TextUtils
import com.cnegroup.kotlindemo.MyApplication
import com.cnegroup.kotlindemo.base.BasePresenter
import com.cnegroup.kotlindemo.base.BaseResultBean
import com.cnegroup.kotlindemo.contract.LoginContract
import com.cnegroup.kotlindemo.data.net.BaseObserver
import com.cnegroup.kotlindemo.data.response.UserBean
import com.cnegroup.kotlindemo.injection.component.DaggerLoginComponent
import com.cnegroup.kotlindemo.model.LoginModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * 登录
 * Created by zhaoyuehai 2018/8/1
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.ILoginView>() {

    @Inject
    lateinit var loginModule: LoginModel

    init {
        DaggerLoginComponent.create().inject(this)
    }

    fun login(username: String, password: String) {
        if (getView() == null) return
        if (checkInput(username, password)) {
            loginModule.login(username, password, object : BaseObserver<BaseResultBean<UserBean>>(this) {

                override fun onNext(result: BaseResultBean<UserBean>) {
                    if (getView() == null) return
                    getView()!!.showToast(result.message)
                    if (result.code.equals("10000")) {
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
                        getView()!!.showToast("登录失败:" + e.message)
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
        if (username.length < 1 || username.length > 16) {
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