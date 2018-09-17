package com.cnegroup.contract

import com.cnegroup.base.IBasePresenter
import com.cnegroup.base.IBaseView
import com.cnegroup.data.bean.VersionDataBean

/**
 * Created by zhaoyuehai 2018/9/14
 */
interface CenterContract {

    interface IView : IBaseView {

        fun showUpdateTipDialog(data: VersionDataBean)

        fun showDownLoadProgress()

        fun downLoadResult(isSuccess: Boolean)

        fun setDownLoadProgress(current: Long?, total: Long?)

    }

    interface IPresenter : IBasePresenter<IView> {

        fun checkVersion()
        
        fun downLoadNewApk(url: String)

        fun cancelDownLoad()
    }
}