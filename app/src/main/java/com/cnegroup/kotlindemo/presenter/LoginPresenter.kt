package com.cnegroup.kotlindemo.presenter

import android.text.TextUtils
import com.cnegroup.kotlindemo.base.BaseApplication
import com.cnegroup.kotlindemo.base.BasePresenter
import com.cnegroup.kotlindemo.base.BaseResultBean
import com.cnegroup.kotlindemo.contract.LoginContract
import com.cnegroup.kotlindemo.data.api.UserApi
import com.cnegroup.kotlindemo.data.net.BaseObserver
import com.cnegroup.kotlindemo.data.net.RetrofitFactory
import com.cnegroup.kotlindemo.data.net.RxSchedulers
import com.cnegroup.kotlindemo.data.response.UserBean
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * 登录
 * Created by zhaoyuehai 2018/8/1
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.ILoginView>() {

    fun login(username: String, password: String) {
        if (getView() == null) return
        if (checkInput(username, password)) {
            RetrofitFactory.instance.create(UserApi::class.java, false)
                    .login(username, password)
                    .compose(RxSchedulers.io_main<BaseResultBean<UserBean>>())
                    .subscribe(object : BaseObserver<BaseResultBean<UserBean>>() {

                        override fun onStart(disposable: Disposable?) {
                            addDisposable(disposable)
                            if (getView() != null)
                                getView()!!.showProgress(true)
                        }

                        override fun onFinish(disposable: Disposable?, e: Throwable?) {
                            deleteDisposable(disposable)
                            if (getView() != null) {
                                if (e != null) {
                                    getView()!!.showToast("登录失败:" + e.message)
                                }
                                getView()!!.showProgress(false)
                            }
                        }


                        override fun onNext(result: BaseResultBean<UserBean>) {
                            if (getView() == null) return
                            getView()!!.showToast(result.message)
                            if (result.code.equals("10000")) {
                                BaseApplication.mContext.setUser(result.data)
                                getView()!!.goMain()
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