package com.example.ballgame.ui.move

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ballgame.R
import com.example.ballgame.core.animation.Animation
import com.example.ballgame.core.constants.AppStrings
import com.example.ballgame.databinding.FragmentMoveBallBinding
import com.example.ballgame.root.MApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoveBallFragment : Fragment() {

    private lateinit var binding: FragmentMoveBallBinding

    @Inject
    lateinit var viewModel: MoveBallViewModel

    @Inject
    lateinit var animation: Animation

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
        binding.tvResult.text = AppStrings.gameOver
        binding.tvResult.setTextColor(resources.getColor(R.color.secondary))
        setCommonAnimation()
    }

    private fun animationOnFail() {
        binding.tvResult.text = AppStrings.youFail
        binding.tvResult.setTextColor(resources.getColor(R.color.red))
        setCommonAnimation()
    }

    private fun setCommonAnimation() {
        animation.resultAnimation(binding.moveBallLayout,binding.tvResult,binding.btnHome,binding.btnTryAgain,{ viewModel.onIntent(MoveBallIntent.OnReset)
            viewModel.onIntent(MoveBallIntent.OnNavigateHome)},{
            viewModel.onIntent(MoveBallIntent.OnReset)
        })
    }



}