package com.cnegroup.kotlindemo.data.net

import android.util.Log
import com.cnegroup.kotlindemo.BuildConfig
import com.cnegroup.kotlindemo.data.net.converter.MyGsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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

    private var needAuthorization: Boolean = true

    private val retrofit: Retrofit

    //初始化
    init {
        retrofit = Retrofit.Builder()
                .client(okHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_SERVER_URL)
                .build()
    }

    fun <T> create(service: Class<T>): T {
        return create(service, true)
    }

    fun <T> create(service: Class<T>, needAuthorization: Boolean): T {
        this.needAuthorization = needAuthorization
        return retrofit.create(service)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(tokenInterceptor())
                .addNetworkInterceptor(logInterceptor())
                .build()
    }

    private fun tokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()
            if (needAuthorization) {
                val request = original.newBuilder()
                        .header("Authorization", "bearer" + " " + "")
//                                UserData.getInstances(BaseApplication.mContext).getValue(UserData.ACCESS_TOKEN, ""))
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build()
                return@Interceptor chain.proceed(request)
            } else {
                return@Interceptor chain.proceed(original)
            }
        }
    }

    private fun logInterceptor(): Interceptor {
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("KotlinDemo", message)
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }
}