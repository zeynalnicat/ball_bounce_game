package com.example.ballgame.core.animation

import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class Animation {


    fun resultAnimation(coreLayout: View,tvResult: TextView,btnHome: AppCompatButton,btnTryAgain: AppCompatButton,btnHomeClick:()->Unit,btnTryClick:()->Unit={}){

        coreLayout.visibility = View.GONE
        tvResult.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }
        btnHome.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }

        btnTryAgain.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }

        btnHome.setOnClickListener {
            btnHomeClick()

        }

        btnTryAgain.setOnClickListener {
            resetAnimation(coreLayout,tvResult,btnHome,btnTryAgain,btnTryClick)
        }

    }

    private fun resetAnimation(coreLayout: View,tvResult: TextView,btnHome: AppCompatButton,btnTryAgain: AppCompatButton,btnTryClick:()->Unit){

        tvResult.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }
        btnHome.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }

        btnTryAgain.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }
        btnTryClick()
        coreLayout.visibility = View.VISIBLE

    }


    fun scoreAnimation(tvScoreInfo: TextView){
        tvScoreInfo.animate().apply {
            duration = 500
            alpha(1f)
            scaleX(1f)
            scaleY(1f)
            withEndAction {
                tvScoreInfo.animate().apply {
                    duration = 300
                    alpha(0f)
                    scaleX(0.5f)
                    scaleY(0.5f)
                    start()
                }
            }
        }.start()

    }
}