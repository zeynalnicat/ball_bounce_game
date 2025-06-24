package com.example.canvasexample.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.play.PlayIntent
import com.example.canvasexample.ui.play.PlayViewModel
import javax.inject.Inject

class PlayLayouts(context: Context, attributeSet: AttributeSet?=null): View(context,attributeSet) {

    @Inject
    lateinit var viewModel: PlayViewModel

    fun onCreate(application: MApplication){
        application.appComponent.inject(this)
    }

    private val mPaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true

    }

    private val paddlePaint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }

    private var ballX = width/2f
    private var ballY = 400f
    private var ballRadius = 200f



    private var paddleWidth = 300f
    private var paddleCenterX =0f
    private var paddleY = 100f


    private var velocityX = 15f
    private var velocityY = 20f

    private var viewWidth = 0
    private var viewHeight = 0


    init {

        attributeSet?.let { setAttrs(context,it) }
    }

    private fun setAttrs(context: Context, attributeSet: AttributeSet){

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val halfWidth = paddleWidth / 2
        val startX = paddleCenterX - halfWidth
        val endX = paddleCenterX + halfWidth

        canvas.drawLine(startX , paddleY, endX , paddleY, paddlePaint)
        canvas.drawCircle(ballX,ballY,ballRadius,mPaint)

        ballX+=velocityX
        ballY+= velocityY

        if(ballX <= 0 || ballX + ballRadius >= viewWidth){
            velocityX = -velocityX
        }

        if(ballX + ballRadius + velocityX in (startX..endX)  &&  ballY+ballRadius+ velocityY <= paddleY + paddlePaint.strokeWidth  ){
            velocityY = -velocityY
            viewModel.onIntent(PlayIntent.RaiseScore)

        }

        if( ballY + ballRadius >= viewHeight && ballY+ballRadius >= paddleY ){
            velocityY = -velocityY
        }

        if(ballY + ballRadius <= 0){
            viewModel.onIntent(PlayIntent.DecreaseScore)
            ballX = width / 2f
            ballY = height / 2f
        }

        postInvalidateOnAnimation()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_MOVE -> {
                paddleCenterX = event.x
                val halfWidth = paddleWidth / 2
                paddleCenterX = paddleCenterX.coerceIn(halfWidth, viewWidth - halfWidth)
                invalidate()
            }
        }
        return true
    }
}