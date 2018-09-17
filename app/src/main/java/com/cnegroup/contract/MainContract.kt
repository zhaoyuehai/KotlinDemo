package com.cnegroup.contract

import com.cnegroup.base.IBasePresenter
import com.cnegroup.base.IBaseView

/**
 * Created by zhaoyuehai 2018/8/1
 */
interface MainContract {

    interface IView : IBaseView {
    }

    interface IPresenter : IBasePresenter<IView> {
    }
}