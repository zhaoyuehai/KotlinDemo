package com.cnegroup.ui.fragment

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.cnegroup.R
import com.cnegroup.base.BaseFragment
import com.cnegroup.widget.NavigationButton

/**
 * Created by zhaoyuehai 2018/8/27
 */
class NavFragment : BaseFragment(), View.OnClickListener {
    private var mContainerId: Int? = null
    private var mContext: Context? = null
    private var mFragmentManager: FragmentManager? = null
    private var mNavStation: NavigationButton? = null
    private var mNavWarning: NavigationButton? = null
    private var mNavOrder: NavigationButton? = null
    private var mNavMe: NavigationButton? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_nav
    }

    override fun initWidget(mRoot: View?) {
        mNavStation = mRoot!!.findViewById(R.id.nav_item_station)
        mNavWarning = mRoot.findViewById(R.id.nav_item_warning)
        mNavOrder = mRoot.findViewById(R.id.nav_item_order)
        mNavMe = mRoot.findViewById(R.id.nav_item_me)
        mNavStation!!.init(R.drawable.tab_icon_new, R.string.main_table_name_station, StationFragment::class.java)
        mNavWarning!!.init(R.drawable.tab_icon_new, R.string.main_table_name_warning, WarningFragment::class.java)
        mNavOrder!!.init(R.drawable.tab_icon_new, R.string.main_table_name_order, OrderFragment::class.java)
        mNavMe!!.init(R.drawable.tab_icon_new, R.string.main_table_name_me, CenterFragment::class.java)
        mNavStation!!.setOnClickListener(this)
        mNavWarning!!.setOnClickListener(this)
        mNavOrder!!.setOnClickListener(this)
        mNavMe!!.setOnClickListener(this)
    }

    override fun init() {
    }

    override fun onClick(nav: View?) {
        if (nav is NavigationButton) {
            doSelect(nav)
        }
    }

    fun setup(context: Context, @IdRes containerId: Int, fragmentManager: FragmentManager, listener: OnNavigationReselectListener) {
        mContext = context
        mContainerId = containerId
        mFragmentManager = fragmentManager
        mOnNavigationReselectListener = listener
        // do clear
        clearOldFragment()
        // do select first
        doSelect(mNavStation)
    }

    private fun clearOldFragment() {
        val transaction = mFragmentManager!!.beginTransaction()
        val fragments = mFragmentManager!!.getFragments()
        if (transaction == null || fragments == null || fragments.size == 0)
            return
        var doCommit = false
        for (fragment in fragments) {
            if (fragment !== this && fragment != null) {
                transaction.remove(fragment)
                doCommit = true
            }
        }
        if (doCommit)
            transaction.commitNow()
    }

    private var mCurrentNavButton: NavigationButton? = null
    private fun doSelect(newNavButton: NavigationButton?) {
        var oldNavButton: NavigationButton? = null
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton
            if (oldNavButton === newNavButton) {
                if (oldNavButton != null) {
                    onReselect(oldNavButton)
                }
                return
            }
            oldNavButton!!.isSelected = false
        }
        newNavButton!!.setSelected(true)
        doTabChanged(oldNavButton, newNavButton)
        mCurrentNavButton = newNavButton
    }

    private fun doTabChanged(oldNavButton: NavigationButton?, newNavButton: NavigationButton?) {
        val ft = mFragmentManager!!.beginTransaction()
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment())
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                val fragment = Fragment.instantiate(mContext, newNavButton.getClx()!!.getName(), null)
                mContainerId?.let { ft.add(it, fragment, newNavButton.tag) }
                newNavButton.setFragment(fragment)
            } else {
                ft.attach(newNavButton.getFragment())
            }
        }
        ft.commit()
    }

    private var mOnNavigationReselectListener: OnNavigationReselectListener? = null
    private fun onReselect(navigationButton: NavigationButton) {
        val listener = mOnNavigationReselectListener
        if (listener != null) {
            listener.onReselect(navigationButton)
        }
    }

    interface OnNavigationReselectListener {
        fun onReselect(navigationButton: NavigationButton)
    }
}