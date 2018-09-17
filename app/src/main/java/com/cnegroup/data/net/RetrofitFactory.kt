package com.cnegroup.data.net

import android.util.Log
import com.cnegroup.BuildConfig
import com.cnegroup.data.net.converter.MyGsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zhaoyuehai 2018/8/2
 */
class RetrofitFactory private constructor() {

    //单例
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: TokenInterceptor

    //初始化
    init {
        interceptor = TokenInterceptor()
        retrofit = Retrofit.Builder()
                .client(okHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_SERVER_URL)
                .build()
    }

    fun <T> create(service: Class<T>): T {
        return create(service, true, true)
    }

    fun <T> create(service: Class<T>, needAuthorization: Boolean, needJsonHeader: Boolean): T {
        interceptor.reSet(needAuthorization, needJsonHeader)
        return retrofit.create(service)
    }

    private fun okHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(logInterceptor())
                .build()
    }

    private fun logInterceptor(): Interceptor {
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("KotlinDemo", message)
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }
}