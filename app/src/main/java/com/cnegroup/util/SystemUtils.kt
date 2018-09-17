package com.cnegroup.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.cnegroup.BuildConfig
import com.cnegroup.MyApplication


/**
 * Created by zhaoyuehai 2018/9/14
 */
object SystemUtils {

    @SuppressLint("MissingPermission", "HardwareIds")
    fun getDeviceId(): String {
        val telephonyMgr = MyApplication.mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return telephonyMgr.getImei()
        } else {
            return telephonyMgr.getDeviceId()
        }
    }

    fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getAppVersionCode(): Int {
        return BuildConfig.VERSION_CODE
    }

}