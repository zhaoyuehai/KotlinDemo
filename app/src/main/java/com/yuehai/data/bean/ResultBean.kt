package com.yuehai.data.bean

/**
 * Created by zhaoyuehai 2018/8/2
 */
data class ResultBean<out T>(val code: String,
                             val message: String,
                             val serviceCode: String,
                             val data: T)