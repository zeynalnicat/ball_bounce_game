package com.example.ballgame.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.ballgame.R
import com.example.ballgame.core.constants.AppStrings
import com.example.ballgame.databinding.FragmentResultBinding
import com.example.ballgame.root.MApplication
import com.example.ballgame.ui.shared.CoreIntent
import com.example.ballgame.ui.shared.CoreViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    @Inject
    lateinit var coreViewModel: CoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        (requireActivity().application as MApplication).appComponent.inject(this)
        setNavigation()
        stateListener()
        return binding.root
    }

    private fun setNavigation() {
        binding.btnHome.setOnClickListener {
            coreViewModel.onIntent(CoreIntent.OnNavigateToHome)
        }

        binding.btnTryAgain.setOnClickListener {
            coreViewModel.onIntent(CoreIntent.OnNavigateToPlay)
        }
    }


    private fun stateListener() {
        lifecycleScope.launch {
            coreViewModel.state.collect {
                if (it.score < 0) {
                    binding.tvScore.setTextColor(resources.getColor(R.color.red))
                    binding.tvScore.text = AppStrings.failed
                } else {
                    binding.tvTime.visibility = View.VISIBLE
                    binding.tvScore.text = "${AppStrings.score} ${it.score}"
                }

            }
        }


    }

}