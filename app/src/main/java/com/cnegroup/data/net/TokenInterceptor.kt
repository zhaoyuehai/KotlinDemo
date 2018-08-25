package com.cnegroup.data.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by zhaoyuehai 2018/8/23
 */
class TokenInterceptor : Interceptor {

    fun setNeedAuthorization(needAuthorization: Boolean) {
        this.needAuthorization = needAuthorization
    }

    private var needAuthorization: Boolean = true

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        if (needAuthorization) {
            val request = original.newBuilder()
                    .header("Authorization", "bearer" + " " + "")
//                                UserData.getInstances(BaseApplication.mContext).getValue(UserData.ACCESS_TOKEN, ""))
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
            return chain.proceed(request)
        } else {
            return chain.proceed(original)
        }
    }

}