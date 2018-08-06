//package com.cnegroup.kotlindemo.widget
//
//import android.content.Context
//import android.support.v4.widget.ViewDragHelper
//import android.util.AttributeSet
//import android.view.View
//import android.view.ViewGroup
//
//
///**
// * Created by zhaoyuehai 2018/8/6
// */
//class SwipeBackLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
//
//    constructor(context: Context?) : this(context, null)
//
//    enum class DragDirectMode {
//        EDGE,
//        VERTICAL,
//        HORIZONTAL
//    }
//
//    enum class DragEdge {
//        LEFT,
//        TOP,
//        RIGHT,
//        BOTTOM
//    }
//
//    private var dragDirectMode = DragDirectMode.EDGE
//    private var dragEdge = DragEdge.TOP
//
//
//    fun seDragEdge(dragEdge: DragEdge) {
//        this.dragEdge = dragEdge
//    }
//
//    fun seDragDirectMode(dragDirectMode: DragDirectMode) {
//        this.dragDirectMode = dragDirectMode
//        if (this.dragDirectMode == DragDirectMode.VERTICAL) {
//            this.dragEdge = DragEdge.TOP
//        } else if (this.dragDirectMode == DragDirectMode.HORIZONTAL) {
//            this.dragEdge = DragEdge.LEFT
//        }
//    }
//
//    private val AUTO_FINISHED_SPEED_LIMIT: Double = 2000.0
//
//    private var viewDragHelper: ViewDragHelper? = null
//
//    private val target: View? = null
//
//    private val scrollChild: View? = null
//
//    private val verticalDragRange = 0
//
//    private val horizontalDragRange = 0
//
//    private val draggingState = 0
//
//    private val draggingOffset: Int = 0
//
//    /**
//     * Whether allow to pull this layout.
//     */
//    private val enablePullToBack = true
//
//    private val BACK_FACTOR = 0.5f
//
//    /**
//     * the anchor of calling finish.
//     */
//    private var finishAnchor = 0f
//
//    fun seFinishAnchor(offset: Float) {
//        this.finishAnchor = offset
//    }
//
//    private var enableFlingBack = true
//
//    fun seEnableFlingBack(b: Boolean) {
//        this.enableFlingBack = b
//    }
//
//    init {
//        viewDragHelper = ViewDragHelper.create(this,1.0f,ViewDragHelper.Callback(china))
//    }
//
//    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
//
//
//    }
//
//
//    private class ViewDragHelperCallBack : ViewDragHelper.Callback() {
//        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
//
//           return child == SwipeBackLayout.this.target && SwipeBackLayout.this.enablePullToBack
//        }
//
//    }
//}