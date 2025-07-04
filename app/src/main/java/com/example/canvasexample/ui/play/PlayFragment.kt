package com.example.canvasexample.ui.play

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.core.animation.Animation
import com.example.canvasexample.databinding.FragmentPlayBinding
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.shared.CoreEffect
import com.example.canvasexample.ui.shared.CoreIntent
import com.example.canvasexample.ui.shared.CoreViewModel
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



        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                binding.countDown.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
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
        binding.tvResult.text = "Time out & Score: ${coreViewModel.state.value.score}"
        binding.tvResult.setTextColor(resources.getColor(R.color.secondary))
        setCommonAnimation()

    }

    private fun animationOnFail(){
        binding.tvResult.text = "YOU FAIL!"
        binding.tvResult.setTextColor(resources.getColor(R.color.red))
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