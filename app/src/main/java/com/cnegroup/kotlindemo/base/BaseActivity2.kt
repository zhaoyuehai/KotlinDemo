package com.cnegroup.kotlindemo.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper

/**
 * Activity基类，无关MVP
 *
 * 实现滑动关闭页面 BGASwipeBackLayout-Android  https://github.com/bingoogolapple/BGASwipeBackLayout-Android
 *
 * Created by zhaoyuehai 2018/8/6
 */
@SuppressLint("Registered")
abstract class BaseActivity2 : AppCompatActivity(), BGASwipeBackHelper.Delegate {

    protected var mSwipeBackHelper: BGASwipeBackHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        initSwipeBackFinish()
        super.onCreate(savedInstanceState)
    }

    private fun initSwipeBackFinish() {
        //必须提前在Application的onCreate方法中执行BGASwipeBackHelper.init 来初始化滑动返回

        mSwipeBackHelper = BGASwipeBackHelper(this, this)

        //以下为自定义配置。默认可以不配

        //设置滑动返回是否可用。默认值为 true
        //mSwipeBackHelper!!.setSwipeBackEnable(true);
        //设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper!!.setIsOnlyTrackingLeftEdge(false);
        //设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper!!.setIsWeChatStyle(true);
        //设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        //mSwipeBackHelper!!.setShadowResId(R.drawable.bga_sbl_shadow);
        //设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper!!.setIsNeedShowShadow(true);
        //设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper!!.setIsShadowAlphaGradient(true);
        //设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        //mSwipeBackHelper!!.setSwipeBackThreshold(0.3f);
        //设置底部导航条是否悬浮在内容上，默认值为 false
        //mSwipeBackHelper!!.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     */
    override fun isSupportSwipeBack(): Boolean {
        return true
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    override fun onSwipeBackLayoutCancel() {

    }

    /**
     *滑动返回执行完毕，销毁当前 Activity
     */
    override fun onSwipeBackLayoutExecuted() {
        mSwipeBackHelper!!.swipeBackward()
    }

    /**
     * 正在滑动返回
     */
    override fun onSwipeBackLayoutSlide(slideOffset: Float) {

    }

    override fun onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper!!.isSliding()) {
            return
        }
        mSwipeBackHelper!!.backward()
    }

    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}