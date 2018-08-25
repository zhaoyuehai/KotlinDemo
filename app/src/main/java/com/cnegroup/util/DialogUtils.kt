package com.cnegroup.util

import android.app.Dialog
import android.content.Context
import com.cnegroup.R

/**
 * Created by zhaoyuehai 2018/8/25
 */
object DialogUtils {


    fun getProgressDialog(context: Context): Dialog {
        return getProgressDialog(context, true, false)
    }

    fun getProgressDialog(context: Context, cancelable: Boolean, cancelTouch: Boolean): Dialog {
        val dialog = Dialog(context, R.style.progressDialog)
        dialog.setContentView(R.layout.layout_progress_dialog)
        dialog.setCancelable(cancelable)
        dialog.setCanceledOnTouchOutside(cancelable)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    fun dismissDialog(progressDialog: Dialog?) {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    fun showDialog(progressDialog: Dialog?) {
        if (progressDialog != null && !progressDialog.isShowing) {
            progressDialog.show()
        }
    }
}