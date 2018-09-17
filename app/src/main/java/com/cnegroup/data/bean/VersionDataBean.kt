package com.cnegroup.data.bean

/**
 * Created by zhaoyuehai 2018/9/14
 */
data class VersionDataBean(val id: Int,
                           val forceUpdate: String, //是否强制更新
                           val versionName: String, //app最新版本号，若没有更新为空
                           val versionCode: String, //app最新版本码，若没有更新为空
                           val url: String, //新版本 APP的文件下载地址，hasAppUpdate为false则没有该项
                           val tipsInfo: String) //新版本 APP的更新内容，hasAppUpdate为false则没有该项
//    {
//        "forceUpdate":"0",
//                "id":1,
//                "tipsInfo":"1:首页引导页网络不好时，卡顿问题。#\n2:电站首页汇总添加loading\n。#3:首页添加点击汇总时，实时请求。#\n4:电站详情页数据显示优化。",
//                "url":"http://10.10.24.38:8088/download/app-formal-release.apk",
//                "versionCode":20100,
//                "versionName":"2.4"
//    }
