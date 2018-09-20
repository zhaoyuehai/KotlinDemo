package com.yuehai.data.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by zhaoyuehai 2018/8/23
 */
class TokenInterceptor : Interceptor {
    private var needAuthorization: Boolean = true
    private var needJsonHeader: Boolean = true

    fun reSet(needAuthorization: Boolean, needJsonHeader: Boolean) {
        this.needAuthorization = needAuthorization
        this.needJsonHeader = needJsonHeader
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder = original.newBuilder()
        if (needJsonHeader) {
            builder.header("Accept", "application/json")
        }
        if (needAuthorization) {
            builder.header("Authorization", "bearer" + " " + "")
//                                UserData.getInstances(BaseApplication.mContext).getValue(UserData.ACCESS_TOKEN, ""))
        }
        val request = builder.method(original.method(), original.body()).build()
        return chain.proceed(request)
    }

}