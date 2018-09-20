package com.yuehai.contract

import com.yuehai.data.bean.VersionDataBean

/**
 * Created by zhaoyuehai 2018/9/14
 */
interface CenterContract {

    interface IView : com.yuehai.base.IBaseView {

        fun showUpdateTipDialog(data: VersionDataBean)

        fun showDownLoadProgress()

        fun downLoadResult(isSuccess: Boolean)

        fun setDownLoadProgress(current: Long?, total: Long?)

    }

    interface IPresenter : com.yuehai.base.IBasePresenter<IView> {

        fun checkVersion()
        
        fun downLoadNewApk(url: String)

        fun cancelDownLoad()
    }
}