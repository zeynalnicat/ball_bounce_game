package com.example.canvasexample.ui.shared

data class CoreState(
    val score: Int = 0,
    val ballRadius: Float = 200f,
    val paddleWidth: Float = 300f,
    val velocityX: Float =15f,
    val velocityY : Float =20f
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}
