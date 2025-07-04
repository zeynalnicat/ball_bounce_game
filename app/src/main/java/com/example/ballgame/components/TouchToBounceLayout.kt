package com.example.ballgame.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.ballgame.R
import com.example.ballgame.root.MApplication
import com.example.ballgame.ui.touch_ball.TouchBallIntent
import com.example.ballgame.ui.touch_ball.TouchBallViewModel
import javax.inject.Inject

class TouchToBounceLayout(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {


    @Inject
    lateinit var viewModel: TouchBallViewModel


    fun onCreate(application: MApplication) {
        application.appComponent.inject(this)
    }

    private val ballRadius = 50f
    private val ballX = 400f


    private val mPaint = Paint().apply {
        color = resources.getColor(R.color.stopwatch)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
    }



    private var fallVelocityY = 10f
    private var clickVelocityY = 100f


    private var viewY = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        viewModel.onIntent(TouchBallIntent.OnIncreaseVelocity(fallVelocityY))

        if (viewModel.state.value.ballY + ballRadius >= viewY - 40f) {

            viewModel.onIntent(TouchBallIntent.OnFail)
        }

        if (viewModel.state.value.ballY + ballRadius <= 20f) {
            viewModel.onIntent(TouchBallIntent.OnWin)
        }

        canvas.drawCircle(ballX, viewModel.state.value.ballY, ballRadius, mPaint)
        canvas.drawLine(ballX + ballRadius,viewModel.state.value.ballY + ballRadius,ballX-ballRadius,viewModel.state.value.ballY - ballRadius,linePaint)


        postInvalidateOnAnimation()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewY = h
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                if (viewModel.state.value.clickCounter % 10 == 0) {
                    clickVelocityY += 5f
                }
                viewModel.onIntent(TouchBallIntent.OnDecreaseVelocity(clickVelocityY))
                invalidate()
            }

            MotionEvent.ACTION_DOWN -> {
                viewModel.onIntent(TouchBallIntent.OnIncreaseClickCount)
            }
        }
        return true

    }
}