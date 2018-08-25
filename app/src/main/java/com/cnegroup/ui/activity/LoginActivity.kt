package com.cnegroup.ui.activity

import android.app.Dialog
import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.cnegroup.R
import com.cnegroup.base.BaseMvpActivity
import com.cnegroup.contract.LoginContract
import com.cnegroup.presenter.LoginPresenter
import com.cnegroup.util.DialogUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录界面
 * Created by zhaoyuehai 2018/8/1
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.IView {

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter()
    }

//    override fun swipeFinishEnable(): Boolean {
//        return true
//    }
//
//    override fun getDragEdge(): SwipeBackLayout.DragEdge {
//        return SwipeBackLayout.DragEdge.TOP
//    }

    override fun attachView() {
        mPresenter.attachView(this)
    }

    override fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onUserNameInputError(s: String) {
        username.error = s
        username.requestFocus()
        username.selectionStart
    }

    override fun onPassWordInputError(s: String) {
        password.error = s
        password.requestFocus()
        password.selectionStart
    }

    override fun init() {
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        login_button.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        mPresenter.login(username.text.toString().trim(), password.text.toString().trim())
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    var progressDialog: Dialog? = null
    override fun showProgress(show: Boolean) {
        if (progressDialog == null)
            progressDialog = DialogUtils.getProgressDialog(this)
        if (show) {
            DialogUtils.showDialog(progressDialog)
        } else {
            DialogUtils.dismissDialog(progressDialog)
        }
    }
}