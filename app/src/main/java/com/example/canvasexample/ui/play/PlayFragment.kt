package com.example.canvasexample.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentPlayBinding
import com.example.canvasexample.root.MApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    @Inject
    lateinit var viewModel: PlayViewModel

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
    }


    private fun stateListener(){
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.tvScore.text = it.score.toString()

            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect {
                when(it){
                    PlayEffect.ShowDecreased -> {
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
                    PlayEffect.ShowRaised -> {
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
                }
            }
        }

    }


}