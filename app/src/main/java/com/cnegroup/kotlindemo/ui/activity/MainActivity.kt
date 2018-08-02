package com.cnegroup.kotlindemo.ui.activity

import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.cnegroup.kotlindemo.R
import com.cnegroup.kotlindemo.base.BaseApplication
import com.cnegroup.kotlindemo.base.BaseMvpActivity
import com.cnegroup.kotlindemo.contract.MainContract
import com.cnegroup.kotlindemo.injection.component.DaggerMainComponent
import com.cnegroup.kotlindemo.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.IMainView {

    override fun attachView() {
        mPresenter.attachView(this)
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun init() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "hi!", Snackbar.LENGTH_LONG)
                    .setAction("hello", {

                    }).show()
        }
        login_tv.setOnClickListener {
            mPresenter.onLoginClick()
        }
        initData()
    }

    override fun initData() {
        val user = BaseApplication.mContext.getUser()
        if (user != null) {
            login_tv.setText("退出")
            main_content_tv.setText(user.toString())
        } else {
            login_tv.setText("登录")
            main_content_tv.setText(R.string.large_text)
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> {
                mPresenter.onSettingClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
