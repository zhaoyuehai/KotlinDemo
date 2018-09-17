package com.cnegroup.data.net.api

import com.cnegroup.data.bean.ResultBean
import com.cnegroup.data.bean.UserBean
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
    fun login(@Field("username") userName: String, @Field("password") password: String): Observable<ResultBean<UserBean>>;
}