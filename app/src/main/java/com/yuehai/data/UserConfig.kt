package com.yuehai.data

import com.yuehai.data.bean.UserBean
import com.yuehai.util.SharedPrefsUtils

/**
 * Created by zhaoyuehai 2018/9/17
 */
class UserConfig private constructor() {
    //单例
    companion object {
        val instance: UserConfig by lazy { UserConfig() }
    }

    private var isLogin: Boolean? = null

    private var user: UserBean? = null

    fun isLogin(): Boolean? {
        if (isLogin == null) {
            isLogin = SharedPrefsUtils.getBoolean("is_login")
        }
        return isLogin
    }

    fun getUser(): UserBean? {
        if (user == null) {
            if (this.isLogin()!!) {
                user = UserBean(SharedPrefsUtils.getString("access_token"),
                        SharedPrefsUtils.getString("token_type"),
                        SharedPrefsUtils.getString("refresh_token"),
                        SharedPrefsUtils.getInt("expires_in"),
                        SharedPrefsUtils.getString("scope"),
                        SharedPrefsUtils.getString("sub"),
                        SharedPrefsUtils.getString("userEnterpriseStatus"),
                        SharedPrefsUtils.getString("auto"),
                        SharedPrefsUtils.getInt("userStatus"),
                        SharedPrefsUtils.getString("userFullName"),
                        SharedPrefsUtils.getString("enterpriseId"),
                        SharedPrefsUtils.getString("right"),
                        SharedPrefsUtils.getString("enterpriseName"),
                        SharedPrefsUtils.getString("username"),
                        SharedPrefsUtils.getString("jti"))
            }
        }
        return user
    }

    fun saveUser(user: UserBean) {
        this.user = user
        SharedPrefsUtils.putString("access_token", user.access_token)
        SharedPrefsUtils.putString("token_type", user.token_type)
        SharedPrefsUtils.putString("refresh_token", user.refresh_token)
        SharedPrefsUtils.putInt("expires_in", user.expires_in)
        SharedPrefsUtils.putString("scope", user.scope)
        SharedPrefsUtils.putString("sub", user.sub)
        SharedPrefsUtils.putString("userEnterpriseStatus", user.userEnterpriseStatus)
        SharedPrefsUtils.putString("auto", user.auto)
        SharedPrefsUtils.putInt("userStatus", user.userStatus)
        SharedPrefsUtils.putString("userFullName", user.userFullName)
        SharedPrefsUtils.putString("enterpriseId", user.enterpriseId)
        SharedPrefsUtils.putString("right", user.right)
        SharedPrefsUtils.putString("enterpriseName", user.enterpriseName)
        SharedPrefsUtils.putString("username", user.username)
        SharedPrefsUtils.putString("jti", user.jti)
        SharedPrefsUtils.putBoolean("is_login", true)
        isLogin = true
    }

    fun clearUser() {
        this.user = null
        SharedPrefsUtils.putString("access_token", "")
        SharedPrefsUtils.putString("token_type", "")
        SharedPrefsUtils.putString("refresh_token", "")
        SharedPrefsUtils.putInt("expires_in", 0)
        SharedPrefsUtils.putString("scope", "")
        SharedPrefsUtils.putString("sub", "")
        SharedPrefsUtils.putString("userEnterpriseStatus", "")
        SharedPrefsUtils.putString("auto", "")
        SharedPrefsUtils.putInt("userStatus", 0)
        SharedPrefsUtils.putString("userFullName", "")
        SharedPrefsUtils.putString("enterpriseId", "")
        SharedPrefsUtils.putString("right", "")
        SharedPrefsUtils.putString("enterpriseName", "")
        SharedPrefsUtils.putString("username", "")
        SharedPrefsUtils.putString("jti", "")
        SharedPrefsUtils.putBoolean("is_login", false)
        isLogin = false
    }

}