package com.yuehai.ui.activity

import android.app.Dialog
import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.yuehai.R
import com.yuehai.base.BaseMvpActivity
import com.yuehai.contract.LoginContract
import com.yuehai.contract.MainContract
import com.yuehai.presenter.CenterPresenter
import com.yuehai.presenter.LoginPresenter
import com.yuehai.util.DialogUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录界面
 * Created by zhaoyuehai 2018/8/1
 */
class LoginActivity :
        BaseMvpActivity<LoginContract.IPresenter>(),
        LoginContract.IView {

    override fun initPresenter(): LoginContract.IPresenter = with(LoginPresenter()){
        attachView(this@LoginActivity)
        return this
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
            progressDialog = DialogUtils.getProgressDialog(this,"登录中...")
        if (show) {
            DialogUtils.showDialog(progressDialog)
        } else {
            DialogUtils.dismissDialog(progressDialog)
        }
    }
}