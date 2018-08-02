package com.cnegroup.kotlindemo.data.net.converter

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.StringReader

/**
 * 响应转换器
 * Created by zhaoyuehai 2018/8/2
 */
class MyGsonResponseBodyConverter<T>(gson: Gson, adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    private val gson: Gson
    private val adapter: TypeAdapter<T>

    init {
        this.gson = gson
        this.adapter = adapter
    }


    override fun convert(value: ResponseBody): T {
        try {
//            try {
//                verify(response);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                throw new ApiException("00000", e.getMessage(), response);
//            }
            val response = value.string()
            val jsonReader = gson.newJsonReader(StringReader(response))
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        } finally {
            value.close()
        }

    }
}