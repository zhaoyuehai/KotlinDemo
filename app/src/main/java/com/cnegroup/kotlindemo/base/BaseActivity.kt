package com.cnegroup.kotlindemo.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Activity基类，无关MVP
 * Created by zhaoyuehai 2018/8/1
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {



    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}