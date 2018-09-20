package com.yuehai.presenter;

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.yuehai.base.BasePresenter
import com.yuehai.contract.CenterContract
import com.yuehai.data.bean.ResultBean
import com.yuehai.data.bean.VersionBean
import com.yuehai.data.net.DownLoadAsyncTask
import com.yuehai.data.net.ResultObserver
import com.yuehai.data.net.RetrofitFactory
import com.yuehai.data.net.RxSchedulers
import com.yuehai.data.net.api.IVersionApi
import com.yuehai.util.SystemUtils

/**
 * Created by zhaoyuehai 2018/9/14
 */
class CenterPresenter : BasePresenter<CenterContract.IView>(), CenterContract.IPresenter {

    private val versionApi: IVersionApi by lazy {
        RetrofitFactory.instance.create(IVersionApi::class.java, false, false)
    }

    override fun checkVersion() {
        versionApi.version(SystemUtils.getVersionName(), SystemUtils.getDeviceId())
                .compose(RxSchedulers.io_main())
                .subscribe(object : ResultObserver<ResultBean<VersionBean>>(this, true) {
                    override fun onNext(o: ResultBean<VersionBean>) {
                        if (getView() == null) return
                        val data = o.data.versionUpdate
                        if (isNewVersion(data.versionCode)) {
                            getView()!!.showUpdateTipDialog(data)
                        } else {
                            getView()!!.showToast("已经是最新版本！")
                        }
                    }

                })
    }

    private fun isNewVersion(serviceVersionCode: String): Boolean {
        val intVersionCode = Integer.parseInt(serviceVersionCode)
        val versionCode = SystemUtils.getAppVersionCode()
        return intVersionCode > versionCode
    }

    private var downTask: AsyncTask<String, Long, Boolean>? = null

    @SuppressLint("StaticFieldLeak")
    override fun downLoadNewApk(url: String) {
        downTask = object : DownLoadAsyncTask("yixun.apk") {

            override fun onPreExecute() {
                super.onPreExecute()
                if (getView() != null)
                    getView()!!.showDownLoadProgress()
            }

            override fun onPostExecute(result: Boolean) {
                super.onPostExecute(result)
                if (getView() != null)
                    getView()!!.downLoadResult(result)
            }

            override fun onProgressUpdate(vararg values: Long?) {
                super.onProgressUpdate(*values)
                Log.e("下载进度" , "--->  "+(values[0]!! / 1024 / 1024) + "/" + (values[1]!! / 1024 / 1024))
                if (getView() != null && values.size == 2) {
                    getView()!!.setDownLoadProgress(values[0], values[1])
                }
            }
        }.execute(url)
    }

    override fun cancelDownLoad() {
        if (downTask != null && !downTask!!.isCancelled())
            downTask!!.cancel(true)
    }
}
