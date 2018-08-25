package com.cnegroup.model

import com.cnegroup.data.api.UserApi
import com.cnegroup.data.net.BaseObserver
import com.cnegroup.data.net.RetrofitFactory
import com.cnegroup.data.net.RxSchedulers
import com.cnegroup.data.response.ResultBean
import com.cnegroup.data.response.UserBean

/**
 * Created by zhaoyuehai 2018/8/23
 */
class LoginModel {

    private val userApi1: UserApi by lazy { RetrofitFactory.instance.create(UserApi::class.java, false) }

    private val userApi2: UserApi by lazy { RetrofitFactory.instance.create(UserApi::class.java, true) }

    fun login(username: String, password: String, observer: BaseObserver<ResultBean<UserBean>>) {
        return userApi1
                .login(username, password)
                .compose(RxSchedulers.io_main<ResultBean<UserBean>>())
                .subscribe(observer)
    }

}