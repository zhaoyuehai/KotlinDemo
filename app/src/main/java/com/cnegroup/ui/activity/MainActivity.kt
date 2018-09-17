package com.cnegroup.ui.activity

import android.widget.Toast
import com.cnegroup.MyApplication
import com.cnegroup.R
import com.cnegroup.base.BaseMvpActivity
import com.cnegroup.contract.MainContract
import com.cnegroup.presenter.MainPresenter
import com.cnegroup.ui.fragment.NavFragment
import com.cnegroup.widget.NavigationButton

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
