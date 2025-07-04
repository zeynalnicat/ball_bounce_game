package com.example.canvasexample.ui.move

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentMoveBallBinding
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.touch_ball.TouchBallIntent
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoveBallFragment : Fragment() {

    private lateinit var binding: FragmentMoveBallBinding

    @Inject
    lateinit var viewModel: MoveBallViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoveBallBinding.inflate(layoutInflater)
        val mApplication = (requireActivity().application as MApplication)
        mApplication.appComponent.inject(this)
        binding.moveBallLayout.onCreate(mApplication)
        stateListener()

        val callBack = object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                animationOnWin()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        return binding.root
    }

    private fun stateListener() {
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.tvBarrierCount.text = it.barrierCounts.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect {
                when (it) {
                    MoveBallEffect.OnFail -> animationOnFail()
                }
            }
        }
    }

    private fun animationOnWin() {
        binding.tvResult.text = "Game is over"
        binding.tvResult.setTextColor(resources.getColor(R.color.secondary))
        setCommonAnimation()
    }

    private fun animationOnFail() {
        binding.tvResult.text = "YOU FAIL!"
        binding.tvResult.setTextColor(resources.getColor(R.color.red))
        setCommonAnimation()
    }

    private fun setCommonAnimation() {
        binding.moveBallLayout.visibility = View.GONE
        binding.tvResult.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }
        binding.btnHome.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }

        binding.btnTryAgain.animate().apply {
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = 500
            start()
        }

        binding.btnHome.setOnClickListener {
            viewModel.onIntent(MoveBallIntent.OnReset)
            viewModel.onIntent(MoveBallIntent.OnNavigateHome)

        }

        binding.btnTryAgain.setOnClickListener {
            resetAnim()
        }
    }

    private fun resetAnim() {
        binding.tvResult.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }
        binding.btnHome.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }

        binding.btnTryAgain.animate().apply {
            alpha(0f)
            scaleY(0f)
            scaleX(0f)
            duration = 300
            start()
        }
        viewModel.onIntent(MoveBallIntent.OnReset)
        binding.moveBallLayout.visibility = View.VISIBLE

    }


}