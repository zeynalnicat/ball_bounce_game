package com.example.ballgame.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.ballgame.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class BackgroundStars(context: Context,attributeSet: AttributeSet): View(context,attributeSet) {

    private val sPaint = Paint().apply {
        isAntiAlias = true
        color = Color.YELLOW
        style = Paint.Style.FILL
    }

    private var starsCount = 0
    private val starRadius = 10f
    private val stars = mutableListOf<Pair<Float,Float>>()

    init{
        val attr = context.obtainStyledAttributes(attributeSet,R.styleable.BackgroundStars)
        val count = attr.getInt(R.styleable.BackgroundStars_starCount,100)
        starsCount = count

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        generateStars()
        for((x,y) in stars){
            drawStars(canvas,x,y,starRadius,starRadius/2.5f)
        }
    }

    private fun generateStars(){
        val rand = Random(System.currentTimeMillis())
        for(i in 0 until starsCount){
            val x = rand.nextFloat() * width
            val y = rand.nextFloat() * height
            stars.add(Pair(x,y))
        }
    }

    private fun drawStars(canvas: Canvas,centerX: Float,centerY: Float,radiusOuter:Float, radiusInner:Float){
        val path = Path()
        val points = 5
        val angle = Math.PI * 2 /points

        for(i in 0 until points*2){
            val r = if(i%2==0) radiusOuter else radiusInner
            val theta = i * angle/2 - Math.PI / 2
            val x = (centerX+r* cos(theta)).toFloat()
            val y = (centerY + r* sin(theta)).toFloat()

            if(i==0) path.moveTo(x,y) else path.lineTo(x,y)
        }
        path.close()
        canvas.drawPath(path,sPaint)

    }

}