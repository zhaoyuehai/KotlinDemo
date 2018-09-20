package com.yuehai.data.net;


import android.os.AsyncTask
import android.os.Environment
import com.yuehai.data.net.api.IVersionApi
import com.yuehai.util.ToastUtils
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by zhaoyuehai 2018/9/12
 */
abstract class DownLoadAsyncTask(private val fileName: String) : AsyncTask<String, Long, Boolean>() {
    // 默认存放文件下载的路径
    private val DEFAULT_SAVE_FILE_PATH: String = Environment.getExternalStorageDirectory().path + File.separator + "yixun" + File.separator + "download" + File.separator

    private var call: Call<ResponseBody>? = null

    override fun doInBackground(vararg params: String?): Boolean {
        val url = params[0] ?: return false
        call = RetrofitFactory
                .instance
                .create(IVersionApi::class.java, false, false)
                .download(url)
        val outP: OutputStream
        val inP: InputStream
        val response = call!!.execute()
        val directory = File(DEFAULT_SAVE_FILE_PATH)
        if (directory.exists() && directory.isDirectory) {

        } else {
            if (!directory.mkdirs()) {
                ToastUtils.showToast("缺少手机存储权限");
                return false;
            }
        }
        val apkFile = File(DEFAULT_SAVE_FILE_PATH + fileName)
        if (apkFile.exists() && apkFile.isFile) {
            apkFile.delete()
        }
        val body = response.body() ?: return false
        //保存到本地
        var oldLength: Long = 0
        var currentLength: Long = 0
        inP = body.byteStream()
        val totalLength = body.contentLength()
        outP = FileOutputStream(apkFile)
        var len: Int
        val buff = ByteArray(1024)
        do {
            len = inP.read(buff)
            if (len != -1) {
                outP.write(buff, 0, len)
                currentLength += len
                if (currentLength / 1024 / 1024 != oldLength) {
                    oldLength = currentLength / 1024 / 1024
                    publishProgress(currentLength, totalLength)
                }
            } else {
                outP.close()
                inP.close()
                return true
            }
        } while (true)
    }

    override fun onCancelled() {
        super.onCancelled()
        if (call != null) {
            if (!call!!.isCanceled()) {
                call!!.cancel();
            }
        }
    }

}
