package com.yuehai.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.yuehai.R
import java.lang.reflect.Method

/**
 * Activity基类，无关MVP
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private var isMiUi = false
        var hasSetStatusBarColor: Boolean = false//是否需要单独设置状态栏颜色
        init {
            //静态域，获取系统版本是否基于MIUI
            try {
                @SuppressLint("PrivateApi") val sysClass: Class<*> = Class.forName("android.os.SystemProperties");
                val getStringMethod: Method = sysClass.getDeclaredMethod("get", String::class.java)
                val version: String = getStringMethod.invoke(sysClass, "ro.miui.ui.version.name") as String
                isMiUi = !(version.compareTo("V9") >= 0 && Build.VERSION.SDK_INT >= 23) && version.compareTo("V6") >= 0 && Build.VERSION.SDK_INT < 24;
            } catch (e: Exception) {
                e.printStackTrace();
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
    }

    private fun initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    @SuppressLint("InlinedApi")
    protected fun setStatusBarDarkMode() {
        val type = getStatusBarLightMode()
        if (type == 1) {
            setMIUIStatusBarDarkMode()
        } else if (type == 2) {
            setMeizuDarkMode(window)
        } else if (type == 3) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else if (type == 4) {
            hasSetStatusBarColor = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isSetStatusBarColor()) {
                val window = window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = resources.getColor(R.color.status_bar_color)

            }
        }
    }

    @SuppressLint("PrivateApi")
    private fun setMIUIStatusBarDarkMode() {
        if (isMiUi) {
            val clazz = window.javaClass
            try {
                val darkModeFlag: Int
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 设置魅族手机状态栏图标颜色风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @return boolean 成功执行返回true
     */
    private fun setMeizuDarkMode(window: Window?): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= 24) {
            return false
        }
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                        .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = value or bit
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return result
    }

    @SuppressLint("InlinedApi")
    private fun getStatusBarLightMode(): Int {
        var result = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isMiUi) {
                result = 1
            } else if (setMeizuDarkMode(window)) {
                result = 2
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                result = 3
            } else {
                result = 4
            }
        }
        return result
    }

    /**
     * 是否设置状态栏颜色
     *
     * @return return
     */
    protected fun isSetStatusBarColor(): Boolean {
        return true
    }

    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}