package com.example.canvasexample.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.example.canvasexample.R

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ClickableViewAccessibility")
class DButton(context: Context, attributeSet: AttributeSet?=null): AppCompatButton(context,attributeSet) {

    private val mPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
        color = Color.BLACK
    }

    init {

        isAllCaps = false
        setTextColor(resources.getColor(R.color.white))

        setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }
            }
            false
        }

        attributeSet?.let { setAttrs(context,it) }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setAttrs(context: Context, attributeSet: AttributeSet){
        val style = context.obtainStyledAttributes(attributeSet,R.styleable.DButton)

        val backgroundDrawable = style.getResourceId(R.styleable.DButton_backgroundDrawable,R.drawable.custom_button)

        setBackgroundResource(backgroundDrawable)
    }

    override fun onDraw(canvas: Canvas) {


        canvas.drawRoundRect(width/4f,height/4f,width/4f,height/4f,30f,30f,mPaint)

        super.onDraw(canvas)

    }
}