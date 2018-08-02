package com.cnegroup.kotlindemo.data.api

import com.cnegroup.kotlindemo.base.BaseResultBean
import com.cnegroup.kotlindemo.data.response.UserBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zhaoyuehai 2018/8/2
 */
interface UserApi {

    @FormUrlEncoded
    @POST("api/v3/login")
    fun login(@Field("username") userName: String, @Field("password") password: String): Observable<BaseResultBean<UserBean>>;
}