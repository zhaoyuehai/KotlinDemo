package com.cnegroup.ui.activity

import android.widget.Toast
import com.cnegroup.MyApplication
import com.cnegroup.R
import com.cnegroup.base.BaseMvpActivity
import com.cnegroup.contract.MainContract
import com.cnegroup.presenter.MainPresenter
import com.cnegroup.ui.fragment.NavFragment
import com.cnegroup.widget.NavigationButton

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.IView, NavFragment.OnNavigationReselectListener {

    override fun onReselect(navigationButton: NavigationButton) {

    }

    override fun getPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun attachView() {
        mPresenter.attachView(this)
    }

    var mNavF: NavFragment? = null

    override fun init() {
        setStatusBarDarkMode()
        mNavF = supportFragmentManager.findFragmentById(R.id.fag_nav) as NavFragment
        mNavF!!.setup(this,R.id.main_container, supportFragmentManager,  this)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_main
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
