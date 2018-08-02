package com.cnegroup.kotlindemo.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.cnegroup.kotlindemo.R
import com.cnegroup.kotlindemo.base.BaseMvpActivity
import com.cnegroup.kotlindemo.contract.LoginContract
import com.cnegroup.kotlindemo.injection.component.DaggerLoginComponent
import com.cnegroup.kotlindemo.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录界面
 * Created by zhaoyuehai 2018/8/1
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.ILoginView {

    override fun attachView() {
        mPresenter.attachView(this)
    }

    override fun injectComponent() {
        DaggerLoginComponent.builder().build().inject(this)
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
        sign_in_button.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        mPresenter.login(username.text.toString().trim(), password.text.toString().trim())
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        login_form.visibility = if (show) View.GONE else View.VISIBLE
        login_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        login_progress.visibility = if (show) View.VISIBLE else View.GONE
        login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
    }
}