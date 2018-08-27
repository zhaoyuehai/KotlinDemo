package com.cnegroup.widget

import android.content.Context
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.cnegroup.R

/**
 * Created by zhaoyuehai 2018/8/27
 */
class NavigationButton : FrameLayout {

    private var mFragment: Fragment? = null
    private var mClx: Class<*>? = null
    private var mIconView: ImageView? = null
    private var mTitleView: TextView? = null
    private var mDot: TextView? = null
    private var mTag: String? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.layout_nav_item, this, true)
        mIconView = findViewById<View>(R.id.nav_iv_icon) as ImageView
        mTitleView = findViewById<View>(R.id.nav_tv_title) as TextView
        mDot = findViewById<View>(R.id.nav_tv_dot) as TextView
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        mIconView!!.setSelected(selected)
        mTitleView!!.setSelected(selected)
    }

    fun showRedDot(count: Int) {
        mDot!!.setVisibility(if (count > 0) View.VISIBLE else View.GONE)
        mDot!!.setText(count.toString())
    }

    fun init(@DrawableRes resId: Int, @StringRes strId: Int, clx: Class<*>) {
        mIconView!!.setImageResource(resId)
        mTitleView!!.setText(strId)
        mClx = clx
        mTag = mClx!!.getName()
    }


    fun getClx(): Class<*>? {
        return mClx
    }

    fun getFragment(): Fragment? {
        return mFragment
    }

    fun setFragment(fragment: Fragment) {
        this.mFragment = fragment
    }

    override fun getTag(): String? {
        return mTag
    }
}