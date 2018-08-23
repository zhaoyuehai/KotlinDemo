package com.cnegroup.kotlindemo.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import javax.inject.Inject

/**
 * Created by zhaoyuehai 2018/8/1
 */
@SuppressLint("Registered")
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity2(), IBaseView {

    //P  dagger注入
    @Inject
    lateinit var mPresenter: T

    //必须是layout布局文件id
    @LayoutRes
    abstract fun getContentViewId(): Int

    abstract fun init()

    abstract fun attachView()
    /**
     * dagger注入
     */
    abstract fun injectComponent()

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        unbinder = ButterKnife.bind(this)
        injectComponent()
        attachView()
        init()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (unbinder != null)
            unbinder?.unbind()
        mPresenter.detachView()
    }
}