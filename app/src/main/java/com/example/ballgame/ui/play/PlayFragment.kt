package com.example.ballgame.ui.play

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ballgame.R
import com.example.ballgame.core.animation.Animation
import com.example.ballgame.databinding.FragmentPlayBinding

import com.example.ballgame.root.MApplication
import com.example.ballgame.ui.shared.CoreEffect
import com.example.ballgame.ui.shared.CoreIntent
import com.example.ballgame.ui.shared.CoreViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    @Inject
    lateinit var coreViewModel: CoreViewModel

    @Inject
    lateinit var viewModel: PlayViewModel

    @Inject
    lateinit var animation: Animation

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MApplication).appComponent.inject(this)
        binding = FragmentPlayBinding.inflate(layoutInflater)
        binding.playLayouts.onCreate(requireActivity().application as MApplication)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateListener()



        countDownTimer = object : CountDownTimer(90000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = (millisUntilFinished / 60000).toInt()
                val seconds = (millisUntilFinished / 1000) - minute*60
                val minutesInText = if(minute<10) "0$minute" else "$minute"
                val secondsInText = if(seconds<10) "0$seconds" else "$seconds"
                binding.countDown.text = minutesInText + ":" + secondsInText
            }

            override fun onFinish() {
                countDownTimer.cancel()
                animationOnWin()
            }
        }

        countDownTimer.start()

        val callBack = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                countDownTimer.onFinish()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callBack)


    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.onFinish()

    }


    private fun stateListener() {
        lifecycleScope.launch {
            coreViewModel.state.collect {
                binding.tvScore.text = it.score.toString()

            }
        }

        lifecycleScope.launch {
            coreViewModel.effect.collect {
                when (it) {
                    CoreEffect.ShowDecreased -> {
                        binding.tvScoreInfo.text = "- 10"
                        binding.tvScoreInfo.setTextColor(resources.getColor(R.color.red))
                        animation.scoreAnimation(binding.tvScoreInfo)


                    }

                    CoreEffect.ShowRaised -> {
                        binding.tvScoreInfo.text = "+ 20"
                        binding.tvScoreInfo.setTextColor(resources.getColor(R.color.secondary))
                        animation.scoreAnimation(binding.tvScoreInfo)

                    }
                    CoreEffect.OnFail -> animationOnFail()
                }
            }
        }



    }

    private fun animationOnWin(){
        binding.tvResult.text = "Time out"
        binding.tvResult.setTextColor(resources.getColor(R.color.secondary))
        setCommonAnimation()

    }

    private fun animationOnFail(){
        binding.tvResult.text = "YOU FAIL!"
        binding.tvResult.setTextColor(resources.getColor(R.color.red))
        countDownTimer.cancel()
        setCommonAnimation()
    }

    private fun setCommonAnimation(){
        animation.resultAnimation(binding.playLayouts,binding.tvResult,binding.btnHome,binding.btnTryAgain,{coreViewModel.onIntent(CoreIntent.OnReset)
            coreViewModel.onIntent(CoreIntent.OnNavigateToHome)},{
            countDownTimer.start()
            coreViewModel.onIntent(CoreIntent.OnReset)
        }

        )
    }


}