package com.cnegroup.model

import com.cnegroup.contract.LoginContract
import com.cnegroup.data.bean.ResultBean
import com.cnegroup.data.bean.UserBean
import com.cnegroup.data.net.ResultObserver
import com.cnegroup.data.net.RetrofitFactory
import com.cnegroup.data.net.RxSchedulers
import com.cnegroup.data.net.api.UserApi

/**
 * Created by zhaoyuehai 2018/8/23
 */
class LoginModel : LoginContract.IModel {

    private val userApi1: UserApi by lazy { RetrofitFactory.instance.create(UserApi::class.java, false, true) }

    private val userApi2: UserApi by lazy { RetrofitFactory.instance.create(UserApi::class.java) }

    override fun login(username: String, password: String, observer: ResultObserver<ResultBean<UserBean>>) {
        return userApi1
                .login(username, password)
                .compose(RxSchedulers.io_main<ResultBean<UserBean>>())
                .subscribe(observer)
    }

}