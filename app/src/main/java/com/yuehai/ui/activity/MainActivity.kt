package com.yuehai.ui.activity

import android.widget.Toast
import com.yuehai.MyApplication
import com.yuehai.R
import com.yuehai.base.BaseMvpActivity
import com.yuehai.contract.MainContract
import com.yuehai.presenter.MainPresenter
import com.yuehai.ui.fragment.NavFragment
import com.yuehai.widget.NavigationButton

/**
 * 主界面
 * Created by zhaoyuehai 2018/8/1
 */
class MainActivity :
        BaseMvpActivity<MainContract.IPresenter>(),
        MainContract.IView,
        NavFragment.OnNavigationReselectListener {

    override fun initPresenter(): MainContract.IPresenter = with(MainPresenter()) {
        attachView(this@MainActivity)
        return this
    }

    var mNavF: NavFragment? = null

    override fun init() {
        setStatusBarDarkMode()
        mNavF = supportFragmentManager.findFragmentById(R.id.fag_nav) as NavFragment
        mNavF!!.setup(this, R.id.main_container, supportFragmentManager, this)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun onReselect(navigationButton: NavigationButton) {

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
