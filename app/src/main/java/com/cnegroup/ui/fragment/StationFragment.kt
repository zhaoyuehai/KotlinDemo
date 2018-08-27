package com.cnegroup.ui.fragment

import android.view.View
import android.view.View.GONE
import com.cnegroup.R
import com.cnegroup.base.BaseActivity
import com.cnegroup.base.BaseFragment

/**
 * Created by zhaoyuehai 2018/8/27
 */
class StationFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.layout_station
    }

    override fun initWidget(mRoot: View?) {
        super.initWidget(mRoot)
        val statusBar: View? = mRoot!!.findViewById(R.id.viewStatusBar)
        if (BaseActivity.hasSetStatusBarColor) {
            statusBar!!.setBackgroundResource(R.color.status_bar_color)
        }
    }

    override fun init() {

    }
}