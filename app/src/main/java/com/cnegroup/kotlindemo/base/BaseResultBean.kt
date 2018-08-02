package com.cnegroup.kotlindemo.base

/**
 * Created by zhaoyuehai 2018/8/2
 */
data class BaseResultBean<out T>(val code: String,
                                 val message: String,
                                 val serviceCode: String,
                                 val data: T)