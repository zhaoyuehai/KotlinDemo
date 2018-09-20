package com.yuehai.data.net.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by zhaoyuehai 2018/8/2
 */
class MyGsonConverterFactory(gson: Gson) : Converter.Factory() {

    private val gson: Gson

    init {
        this.gson = gson
    }

    companion object {

        fun create(gson: Gson): MyGsonConverterFactory {
            return MyGsonConverterFactory(gson)
        }

        fun create(): MyGsonConverterFactory {
            return create(Gson())
        }
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MyGsonRequestBodyConverter(gson, adapter)
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MyGsonResponseBodyConverter(gson, adapter)
    }

}