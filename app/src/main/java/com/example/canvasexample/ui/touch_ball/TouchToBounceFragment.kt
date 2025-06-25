package com.example.canvasexample.ui.touch_ball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentTouchToBounceBinding
import com.example.canvasexample.root.MApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class TouchToBounceFragment : Fragment() {
    private lateinit var binding: FragmentTouchToBounceBinding


    @Inject
    lateinit var viewModel: TouchBallViewModel

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
        binding.layoutTouch.visibility = View.GONE
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
            viewModel.onIntent(TouchBallIntent.OnReset)
            viewModel.onIntent(TouchBallIntent.OnNavigateToHome)

        }

        binding.btnTryAgain.setOnClickListener {
            resetAnim()
        }
    }

    private fun resetAnim(){
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
        viewModel.onIntent(TouchBallIntent.OnReset)
        binding.layoutTouch.visibility = View.VISIBLE

    }



    }