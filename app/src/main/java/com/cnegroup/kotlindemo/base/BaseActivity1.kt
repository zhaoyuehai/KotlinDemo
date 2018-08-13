package com.cnegroup.kotlindemo.base

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import com.cnegroup.kotlindemo.R
import com.cnegroup.kotlindemo.widget.SwipeBackLayout

/**
 * Activity基类，无关MVP
 *
 * SwipeBackLayout实现滑动关闭页面
 *
 * Created by zhaoyuehai 2018/8/6
 */
@SuppressLint("Registered")
abstract class BaseActivity1 : AppCompatActivity() {

    private var swipeBackLayout: SwipeBackLayout? = null
    private var ivShadow: ImageView? = null

    /**
     *是否滑动关闭
     */
    abstract fun swipeFinishEnable(): Boolean

    protected open fun getDragEdge(): SwipeBackLayout.DragEdge {
        return SwipeBackLayout.DragEdge.LEFT
    }

    override fun setContentView(layoutResID: Int) {
        if (swipeFinishEnable()) {
            super.setContentView(getContainer())
            swipeBackLayout!!.addView(View.inflate(this, layoutResID, null))
        } else {
            super.setContentView(layoutResID)
        }
    }


    private fun getContainer(): View {
        val container = RelativeLayout(this)
        swipeBackLayout = SwipeBackLayout(this)

        swipeBackLayout!!.setDragEdge(getDragEdge())
//        swipeBackLayout!!.setOnSwipeBackListener({ fractionAnchor, fractionScreen ->
//            Log.i("setOnSwipeBackListener", "----fractionAnchor:" + fractionAnchor + "---fractionScreen:" + fractionScreen)
//        })
        ivShadow = ImageView(this)
        ivShadow!!.setBackgroundColor(ContextCompat.getColor(this,R.color.black_p50))
        container.addView(ivShadow, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        container.addView(swipeBackLayout)
        return container
    }


    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}