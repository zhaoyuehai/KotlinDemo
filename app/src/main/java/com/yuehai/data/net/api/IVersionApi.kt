package com.yuehai.data.net.api;

import com.yuehai.data.bean.ResultBean
import com.yuehai.data.bean.VersionBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Created by zhaoyuehai 2018/9/12
 */
interface IVersionApi {
    /**
     * 检查新版本
     * @return  Observable
     */
    @GET("api/v3/app/version")
    fun version(@Query("versionName") versionName: String,
                @Query("deviceId") deviceId: String): Observable<ResultBean<VersionBean>>

    /**
     * 下载文件
     * @param url 文件下载地址
     * @return Observable
     */
    @Streaming
    @GET
    fun download(@Url url: String): Call<ResponseBody>
}
