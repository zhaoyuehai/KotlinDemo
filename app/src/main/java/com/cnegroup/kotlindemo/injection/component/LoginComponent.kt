package com.cnegroup.kotlindemo.injection.component

import com.cnegroup.kotlindemo.presenter.LoginPresenter
import com.cnegroup.kotlindemo.ui.activity.LoginActivity
import dagger.Component

/**
 * Created by zhaoyuehai 2018/8/2
 */
@Component
interface LoginComponent {

    fun inject(activity: LoginActivity)

    fun inject(presenter: LoginPresenter)

}