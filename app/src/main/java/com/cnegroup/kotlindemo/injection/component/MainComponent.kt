package com.cnegroup.kotlindemo.injection.component

import com.cnegroup.kotlindemo.ui.activity.MainActivity
import dagger.Component

/**
 * Created by zhaoyuehai 2018/8/2
 */
@Component
interface MainComponent {

    fun inject(activity: MainActivity)

}