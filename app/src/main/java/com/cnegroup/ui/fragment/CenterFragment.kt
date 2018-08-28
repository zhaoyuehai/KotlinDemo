package com.cnegroup.ui.fragment

import android.view.View
import com.cnegroup.R
import com.cnegroup.base.BaseFragment
import com.cnegroup.widget.SolarSystemView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by zhaoyuehai 2018/8/27
 */
class CenterFragment : BaseFragment() {

    var mLlShowInfo: View? = null
    var mFlUserInfoIconContainer: View? = null
    var mIvCircle: CircleImageView? = null
    var mSolarSystem: SolarSystemView? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_center
    }

    override fun initWidget(mRoot: View?) {
        super.initWidget(mRoot)
        mLlShowInfo = mRoot!!.findViewById(R.id.ll_show_my_info)
        mFlUserInfoIconContainer = mRoot.findViewById(R.id.user_info_icon_container)
        mIvCircle = mRoot.findViewById(R.id.iv_circle)
        mSolarSystem = mRoot.findViewById(R.id.user_view_solar_system)
    }

    override fun init() {
        initSolar()
    }

    private var mMaxRadius: Int? = null
    private var mR: Int? = null
    private var mPx: Float? = null
    private var mPy: Float? = null
    private fun initSolar() {
        mRoot?.post({
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
        })
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