package com.example.canvasexample.ui.touch_ball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.core.animation.Animation
import com.example.canvasexample.databinding.FragmentTouchToBounceBinding
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.shared.CoreIntent
import kotlinx.coroutines.launch
import javax.inject.Inject

class TouchToBounceFragment : Fragment() {
    private lateinit var binding: FragmentTouchToBounceBinding


    @Inject
    lateinit var viewModel: TouchBallViewModel

    @Inject
    lateinit var animation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTouchToBounceBinding.inflate(layoutInflater)
        val application = (requireActivity().application as MApplication)
        application.appComponent.inject(this)

        binding.layoutTouch.onCreate(application)


        stateListener()
        return binding.root
    }

    private fun stateListener(){
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.tvClickCounter.text = it.clickCounter.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect {
                when(it){
                    TouchBallEffect.OnFail -> animationOnFail()
                    TouchBallEffect.OnWin ->animationOnWin()
                }
            }
        }
    }

    private fun animationOnWin(){
        binding.tvResult.text = "YOU WIN!"
        binding.tvResult.setTextColor(resources.getColor(R.color.secondary))
        setCommonAnimation()
    }

    private fun animationOnFail(){
        binding.tvResult.text = "YOU FAIL!"
        binding.tvResult.setTextColor(resources.getColor(R.color.red))
        setCommonAnimation()
    }

    private fun setCommonAnimation(){
        animation.resultAnimation(binding.layoutTouch,binding.tvResult,binding.btnHome,binding.btnTryAgain,{   viewModel.onIntent(TouchBallIntent.OnReset)
            viewModel.onIntent(TouchBallIntent.OnNavigateToHome)},{
            viewModel.onIntent(TouchBallIntent.OnReset)
        })
    }


    }