package com.cnegroup.kotlindemo.model

import com.cnegroup.kotlindemo.base.BaseResultBean
import com.cnegroup.kotlindemo.data.api.UserApi
import com.cnegroup.kotlindemo.data.net.BaseObserver
import com.cnegroup.kotlindemo.data.net.RetrofitFactory
import com.cnegroup.kotlindemo.data.net.RxSchedulers
import com.cnegroup.kotlindemo.data.response.UserBean
import javax.inject.Inject

/**
 * Created by zhaoyuehai 2018/8/23
 */
class LoginModel @Inject constructor() {

    private val userApi: UserApi by lazy { RetrofitFactory.instance.create(UserApi::class.java, false) }

    fun login(username: String, password: String, observer: BaseObserver<BaseResultBean<UserBean>>) {
        return userApi
                .login(username, password)
                .compose(RxSchedulers.io_main<BaseResultBean<UserBean>>())
                .subscribe(observer)
    }

}