package com.example.canvasexample.ui.play

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentPlayBinding
import com.example.canvasexample.root.MApplication
import com.example.canvasexample.ui.shared.CoreEffect
import com.example.canvasexample.ui.shared.CoreViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    @Inject
    lateinit var coreViewModel: CoreViewModel

    @Inject
    lateinit var viewModel: PlayViewModel

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
                viewModel.onIntent(PlayIntent.OnNavigateToResult)
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
                        binding.tvScoreInfo.animate().apply {
                            duration = 700
                            alpha(1f)
                            scaleX(1f)
                            scaleY(1f)
                            withEndAction {
                                binding.tvScoreInfo.animate().apply {
                                    duration = 300
                                    alpha(0f)
                                    scaleX(0.5f)
                                    scaleY(0.5f)
                                    start()
                                }
                            }
                        }.start()


                    }

                    CoreEffect.ShowRaised -> {
                        binding.tvScoreInfo.text = "+ 20"
                        binding.tvScoreInfo.setTextColor(resources.getColor(R.color.secondary))
                        binding.tvScoreInfo.animate().apply {
                            duration = 700
                            alpha(1f)
                            scaleX(1f)
                            scaleY(1f)
                                .withEndAction {
                                    binding.tvScoreInfo.animate().apply {
                                        duration = 300
                                        alpha(0f)
                                        scaleX(0.5f)
                                        scaleY(0.5f)
                                        start()
                                    }
                                }
                        }.start()

                    }

                    CoreEffect.NotifyNavigation -> viewModel.onIntent(PlayIntent.OnNavigateToResult)
                }
            }
        }

    }


}