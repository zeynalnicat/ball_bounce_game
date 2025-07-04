package com.example.canvasexample.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.canvasexample.R
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.move.MoveBallIntent
import com.example.canvasexample.ui.move.MoveBallViewModel
import javax.inject.Inject

class MoveBallLayout(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val ballPaint = Paint().apply {
        color = resources.getColor(R.color.stopwatch)
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    @Inject
    lateinit var viewModel: MoveBallViewModel

    fun onCreate(application: MApplication) {
        application.appComponent.inject(this)
    }

    private val barrierPaint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        strokeWidth = 10f
        style = Paint.Style.FILL
    }

    private var ballX = 0f
    private var ballY = 0f
    private var ballRadius = 40f

    private var barrierPaddleWidth: Float? = null

    private var barrierPaddleCenterX: Float? = null

    private var viewWidth = 0
    private var viewHeight = 0

    private var barrierY = 0f

    private var velocityBarrierY = 20f


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint)
        barrierY += velocityBarrierY

        if (barrierPaddleWidth == null) {
            barrierPaddleWidth = (0..viewWidth / 2).random().toFloat()
        }

        if (barrierPaddleCenterX == null) {
            barrierPaddleCenterX =
                (200..viewWidth - barrierPaddleWidth!!.toInt()).random().toFloat()
        }



        if (barrierY >= ballY + ballRadius + 300f) {
            barrierPaddleWidth = null
            barrierPaddleCenterX = null
            barrierY = 0f
            viewModel.onIntent(MoveBallIntent.OnCrossBarrier)
        }

        if (barrierPaddleCenterX != null && barrierPaddleWidth != null) {
            val barrierTop = barrierY - barrierPaint.strokeWidth / 2
            val barrierBottom = barrierY + barrierPaint.strokeWidth / 2

            val ballTop = ballY - ballRadius
            val ballBottom = ballY + ballRadius

            val ballHitsBarrierVertically = ballBottom >= barrierTop && ballTop <= barrierBottom
            val ballHitsBarrierHorizontally =
                ballX in (barrierPaddleCenterX!! - barrierPaddleWidth!!..barrierPaddleCenterX!! + barrierPaddleWidth!!)

            if (ballHitsBarrierVertically && ballHitsBarrierHorizontally) {
                ballX = width / 2f
                ballY = height - 200f
                barrierY = 0f
                barrierPaddleWidth = null
                barrierPaddleCenterX = null
                viewModel.onIntent(MoveBallIntent.OnFail)
            }
        }


        if (barrierPaddleCenterX != null && barrierPaddleWidth != null) {
            canvas.drawLine(
                barrierPaddleCenterX!! - barrierPaddleWidth!!,
                barrierY,
                barrierPaddleCenterX!! + barrierPaddleWidth!!,
                barrierY,
                barrierPaint
            )
        }

        postInvalidateOnAnimation()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h


        if (ballX == 0f && ballY == 0f) {
            ballX = w / 2f
            ballY = h - 400f
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                ballY = event.y - ballRadius
                ballX = event.x - ballRadius
                invalidate()
            }

        }
        return true

    }
}