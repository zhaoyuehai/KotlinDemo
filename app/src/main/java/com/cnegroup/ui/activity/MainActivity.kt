package com.cnegroup.ui.activity

import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cnegroup.MyApplication
import com.cnegroup.R
import com.cnegroup.base.BaseMvpActivity
import com.cnegroup.contract.MainContract
import com.cnegroup.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.IView {
    override fun getPresenter(): MainPresenter {
        return MainPresenter()
    }

//    override fun swipeFinishEnable(): Boolean {
//        return true
//    }

    override fun attachView() {
        mPresenter.attachView(this)
    }

    override fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
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
        val user = MyApplication.mContext.getUser()
        if (user != null) {
            login_tv.setText("退出")
            main_content_tv.setText(user.toString())
        } else {
            login_tv.setText("登录")
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

    private var exitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            MyApplication.mContext.exit()
        }
    }

    override fun isSupportSwipeBack(): Boolean {
        return false
    }
}
