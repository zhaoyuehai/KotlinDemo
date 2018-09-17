package com.cnegroup.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cnegroup.MyApplication
import com.cnegroup.R
import com.cnegroup.base.BaseMvpFragment
import com.cnegroup.contract.CenterContract
import com.cnegroup.data.UserConfig
import com.cnegroup.data.bean.VersionDataBean
import com.cnegroup.presenter.CenterPresenter
import com.cnegroup.ui.activity.LoginActivity
import com.cnegroup.util.SystemUtils
import com.cnegroup.widget.SolarSystemView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * 个人中心
 * Created by zhaoyuehai 2018/8/27
 */
class CenterFragment :
        BaseMvpFragment<CenterContract.IPresenter>(),
        CenterContract.IView,
        View.OnClickListener {

    override fun initPresenter(): CenterContract.IPresenter = with(CenterPresenter()) {
        attachView(this@CenterFragment)
        return this
    }

    private var mLlShowInfo: View? = null
    private var mFlUserInfoIconContainer: View? = null
    private var mIvCircle: CircleImageView? = null
    private var mSolarSystem: SolarSystemView? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_center
    }

    override fun initView(mRoot: View?) {
        super.initView(mRoot)
        mLlShowInfo = mRoot!!.findViewById(R.id.ll_show_my_info)
        mFlUserInfoIconContainer = mRoot.findViewById(R.id.user_info_icon_container)
        mIvCircle = mRoot.findViewById(R.id.iv_circle)
        mSolarSystem = mRoot.findViewById(R.id.user_view_solar_system)
        initSolar()
        initView()
        checkLogin()
    }

    var test_btn2: TextView? = null
    var tv_nick: TextView? = null

    private fun initView() {
        val test_btn1: TextView = findViewById(R.id.test_btn1)
        val iv_circle: ImageView = findViewById(R.id.iv_circle)
        test_btn2 = findViewById(R.id.test_btn2)
        tv_nick = findViewById(R.id.tv_nick)
        val versionName = SystemUtils.getVersionName()
        test_btn1.text = "检查更新(v_$versionName)"
        test_btn1.setOnClickListener(this)
        test_btn2!!.text = "退出登录"
        test_btn2!!.setOnClickListener(this)
        iv_circle.setOnClickListener(this)
    }

    private fun checkLogin() {
        if (UserConfig.instance.isLogin()!!) {
            tv_nick!!.text = UserConfig.instance.getUser()!!.username
            test_btn2!!.visibility = View.VISIBLE
        } else {
            tv_nick!!.text = "未登录"
            test_btn2!!.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.test_btn1 -> mPresenter.checkVersion()
            R.id.test_btn2 -> {
                UserConfig.instance.clearUser()
                checkLogin()
                //TODO 发广播通知
            }
            R.id.iv_circle -> {
                if (UserConfig.instance.isLogin()!!) {

                } else {
                    startActivity(Intent(context, LoginActivity::class.java))
                }
            }
        }
    }

    override fun showUpdateTipDialog(data: VersionDataBean) {
        val isForceUpdate = data.forceUpdate == "1"
        val builder = AlertDialog.Builder(context)
        val info: Array<String> = if (data.tipsInfo.contains("#")) {
            data.tipsInfo.split("#").toTypedArray()
        } else {
            arrayOf(data.tipsInfo)
        }
        builder.setItems(info, null)
        builder.setTitle(getString(R.string.have_new_version) + data.versionName)
        builder.setPositiveButton(R.string.update_version_btn2) { dialog, _ ->
            dialog.dismiss()
            mPresenter.downLoadNewApk(data.url)
        }
        builder.setCancelable(true)
        if (isForceUpdate) {
            builder.setOnCancelListener { MyApplication.mContext.exit() }
        } else {
            builder.setNegativeButton(R.string.update_version_btn1) { dialog, _ ->
                dialog.dismiss()
            }
        }
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private var downLoadProgress: ProgressDialog? = null
    override fun showDownLoadProgress() {
        downLoadProgress = ProgressDialog(context)
        downLoadProgress!!.setCanceledOnTouchOutside(false)
        downLoadProgress!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        downLoadProgress!!.setCancelable(false)
        downLoadProgress!!.progress = 0
        downLoadProgress!!.setProgressNumberFormat("%1dM/%2dM")//这里设置的是进度条下面显示的文件大小和下载了多少
        downLoadProgress!!.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.text_cancel)) { dialog, _ ->
            mPresenter.cancelDownLoad()
            dialog.dismiss()
        }
        downLoadProgress!!.setTitle(R.string.download_reday)
        downLoadProgress!!.show()
    }

    override fun downLoadResult(isSuccess: Boolean) {
        if (downLoadProgress != null) {
            downLoadProgress!!.setTitle(if (isSuccess) R.string.download_success else R.string.download_fail)
            downLoadProgress!!.dismiss()
        }
    }

    override fun setDownLoadProgress(current: Long?, total: Long?) {
        if (downLoadProgress != null) {
            downLoadProgress!!.setTitle(R.string.download)
            if (current != null && total != null) {
                Log.e("界面 下载进度", "--->  " + (current / 1024 / 1024) + "/" + (total / 1024 / 1024))
                downLoadProgress!!.max = (total / 1024 / 1024).toInt()
                downLoadProgress!!.progress = (current / 1024 / 1024).toInt()
            }
        }
    }


    private var mMaxRadius: Int? = null
    private var mR: Int? = null
    private var mPx: Float? = null
    private var mPy: Float? = null
    private fun initSolar() {
        mRoot?.post {
            val width = mLlShowInfo!!.width
            val rlShowInfoX = mLlShowInfo!!.x
            val height = mFlUserInfoIconContainer!!.height
            val y1 = mFlUserInfoIconContainer!!.y

            val x = mIvCircle!!.x
            val y = mIvCircle!!.y
            val portraitWidth = mIvCircle!!.width

            mPx = x + +rlShowInfoX + (width shr 1).toFloat()
            mPy = y1 + y + (height - y) / 2
            mMaxRadius = (mSolarSystem!!.height - mPy!! + 250).toInt()
            mR = portraitWidth shr 1
            updateSolar(mPx!!, mPy!!)
        }
    }

    /**
     * update solar
     *
     * @param px float
     * @param py float
     */
    private fun updateSolar(px: Float, py: Float) {

        val solarSystemView = mSolarSystem
        val random = Random(System.currentTimeMillis())
        val maxRadius = mMaxRadius
        val r = mR
        solarSystemView!!.clear()
        var i = 40
        var radius = r!! + i
        while (radius <= maxRadius!!) {
            val planet = SolarSystemView.Planet()
            planet.isClockwise = random.nextInt(10) % 2 == 0
            planet.angleRate = (random.nextInt(35) + 1) / 1000f
            planet.radius = radius
            solarSystemView.addPlanets(planet)
            i = (i * 1.4).toInt()
            radius += i
        }
        solarSystemView.setPivotPoint(px, py)
    }
}