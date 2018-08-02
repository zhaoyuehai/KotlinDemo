package com.cnegroup.kotlindemo.data.net.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.Charset

/**
 * 请求转换器
 * Created by zhaoyuehai 2018/8/2
 */
class MyGsonRequestBodyConverter<T>(gson: Gson, adapter: TypeAdapter<T>) : Converter<T, RequestBody> {

    companion object {
        private val MEDIA_TYPE: MediaType? = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8: Charset = Charset.forName("UTF-8")

    }

    private val gson: Gson
    private val adapter: TypeAdapter<T>

    init {
        this.gson = gson
        this.adapter = adapter
    }

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }

}