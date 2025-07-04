package com.example.ballgame.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ballgame.databinding.FragmentMenuBinding
import com.example.ballgame.root.MApplication
import com.example.ballgame.ui.shared.CoreIntent
import com.example.ballgame.ui.shared.CoreViewModel
import com.example.ballgame.ui.shared.Difficulty
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    @Inject
    lateinit var viewModel: MenuViewModel

    @Inject
    lateinit var coreViewModel: CoreViewModel

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        (requireActivity().application as MApplication).appComponent.inject(this)


        binding.btnPlay.setOnClickListener {
            coreViewModel.onIntent(CoreIntent.OnSetDifficulty(Difficulty.EASY))
            viewModel.onIntent(MenuIntent.OnNavigateToPlay)
        }

        binding.btnMedium.setOnClickListener {
            coreViewModel.onIntent(CoreIntent.OnSetDifficulty(Difficulty.MEDIUM))
            viewModel.onIntent(MenuIntent.OnNavigateToPlay)
        }

        binding.btnHard.setOnClickListener {
            coreViewModel.onIntent(CoreIntent.OnSetDifficulty(Difficulty.HARD))
            viewModel.onIntent(MenuIntent.OnNavigateToPlay)
        }

        binding.btnTouchToBall.setOnClickListener {
            viewModel.onIntent(MenuIntent.OnNavigateToBounceBall)
        }

        binding.btnBarrier.setOnClickListener {
            viewModel.onIntent(MenuIntent.OnNavigateToMoveBall)
        }
        return binding.root
    }


}