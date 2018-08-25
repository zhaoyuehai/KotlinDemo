package com.cnegroup.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.cnegroup.data.response.UserBean
import com.cnegroup.ui.activity.MainActivity

/**
 *
 * Created by zhaoyuehai 2018/8/1
 */
abstract class BaseApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {


    private var mActivityList = arrayListOf<Activity>()
    private var runningActivityList = arrayListOf<Activity>()

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(var1: Activity, var2: Bundle?) {
        mActivityList.add(var1)
    }

    override fun onActivityStarted(var1: Activity) {
        runningActivityList.add(var1)
    }

    override fun onActivityStopped(var1: Activity) {
        runningActivityList.remove(var1)
    }

    override fun onActivityDestroyed(var1: Activity) {
        mActivityList.remove(var1)
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle?) {
    }

    /**
     * 获取当前应用状态
     * @return 0:后台 1:主界面 2:其他页面
     */
    open fun getForgetInfo(): Int {
        if (runningActivityList.size <= 0) {
            return 0
        } else if (runningActivityList.get(0) is MainActivity) {
            return 1
        } else {
            return 2
        }
    }

    /**
     * 退出应用
     */
    open fun exit() {
        for (activity in mActivityList) {
            if (!activity.isFinishing()) {
                activity.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    private var user: UserBean? = null

    fun setUser(user: UserBean?) {
        this.user = user
    }

    fun getUser(): UserBean? {
        return user
    }

    protected fun finalize() {
        // 终止化逻辑
        Log.i("finalized--------:", this.javaClass.simpleName);
    }
}