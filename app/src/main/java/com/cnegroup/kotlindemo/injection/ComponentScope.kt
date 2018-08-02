package com.cnegroup.kotlindemo.injection

import javax.inject.Scope

/**
 * 组件级别的作用域
 * Created by zhaoyuehai 2018/8/1
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ComponentScope