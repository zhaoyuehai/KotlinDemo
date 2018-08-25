package com.cnegroup.util

import android.text.TextUtils
import android.widget.Toast
import com.cnegroup.MyApplication

/**
 * Created by zhaoyuehai 2018/8/24
 */
object ToastUtils {
    private var oldMsg: String? = null
    private var toast: Toast? = null
    private var oneTime: Long = 0
    private var twoTime: Long = 0

    fun showToast(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mContext, msg, Toast.LENGTH_SHORT)
                toast!!.show()
                oneTime = System.currentTimeMillis()
            } else {
                twoTime = System.currentTimeMillis()
                if (msg == oldMsg) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast!!.show()
                    }
                } else {
                    oldMsg = msg
                    toast!!.setText(msg)
                    toast!!.show()
                }
            }
            oneTime = twoTime
        }
    }

    fun showToast(resId: Int) {
        showToast(MyApplication.mContext.getString(resId))
    }


}