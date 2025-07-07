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
import com.example.ballgame.ui.shared.CoreIntent
import com.example.ballgame.ui.shared.CoreViewModel
import javax.inject.Inject

class PlayLayouts(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet) {

    @Inject
    lateinit var viewModel: CoreViewModel

    fun onCreate(application: MApplication) {
        application.appComponent.inject(this)
    }

    private val mPaint = Paint().apply {
        color = resources.getColor(R.color.stopwatch)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val paddlePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }

    private val linePaint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
    }

    private var ballX = width /2f
    private var ballY = 400f

    private var paddleCenterX = 0f
    private var paddleY = 100f



    private var viewWidth = 0
    private var viewHeight = 0


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val halfWidth = viewModel.state.value.paddleWidth / 2
        val startX = paddleCenterX - halfWidth
        val endX = paddleCenterX + halfWidth

        canvas.drawLine(startX, paddleY, endX, paddleY, paddlePaint)
        canvas.drawCircle(ballX, ballY, viewModel.state.value.ballRadius, mPaint)
        canvas.drawLine(ballX + viewModel.state.value.ballRadius,ballY + viewModel.state.value.ballRadius,ballX-viewModel.state.value.ballRadius,ballY - viewModel.state.value.ballRadius,linePaint)

        ballX += viewModel.state.value.velocityX
        ballY += viewModel.state.value.velocityY

        if (ballX <= 0 || ballX +  viewModel.state.value.ballRadius >= viewWidth) {
            viewModel.onIntent(CoreIntent.OnChangeVelocityX)
        }

        val paddleTop = paddleY - paddlePaint.strokeWidth/2
        val paddleBottom = paddleY + paddlePaint.strokeWidth/2

        val ballTop = ballY - viewModel.state.value.ballRadius
        val ballBottom = ballY + viewModel.state.value.ballRadius

        val ballHitsVertically = ballBottom >= paddleTop && ballTop <= paddleBottom
        val ballHitsHorizontally = ballX  in (startX - 40f..endX + 40f)

        if (ballHitsHorizontally && ballHitsVertically ) {
            viewModel.onIntent(CoreIntent.OnChangeVelocityY)
            viewModel.onIntent(CoreIntent.RaiseScore)
            ballY = (ballY  + paddlePaint.strokeWidth + 1)

        }

        if (ballY + viewModel.state.value.ballRadius>= viewHeight && ballY +  viewModel.state.value.ballRadius >= paddleY) {
            viewModel.onIntent(CoreIntent.OnChangeVelocityY)

        }

        if (ballY +  viewModel.state.value.ballRadius <= 0) {
            viewModel.onIntent(CoreIntent.DecreaseScore)
            ballX = width/2f
            ballY = height - 200f
        }

        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                paddleCenterX = event.x
                val halfWidth =  viewModel.state.value.paddleWidth / 2
                paddleCenterX = paddleCenterX.coerceIn(halfWidth, viewWidth - halfWidth)
                invalidate()
            }
        }
        return true
    }
}