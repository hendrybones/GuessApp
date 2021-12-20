package com.example.guessapp.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.guessapp.R
import com.example.guessapp.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =DataBindingUtil.inflate<FragmentScoreBinding>(inflater,R.layout.fragment_score,container,false)

        viewModelFactory= ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments).score)
        viewModel=viewModelProviders.of(this,viewModelFactory)
            .get(ScoreViewModel ::class.java)
        binding.scoreText.text=ScoreFragmentArgs.fromBundle(arguments).score.toString()
        binding.playAgainButton.setOnClickListener { onPlayAgain() }


        // Get args using by navArgs property delegate
//        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
//        binding.scoreText.text = scoreFragmentArgs.score.toString()
//        binding.playAgainButton.setOnClickListener { onPlayAgain() }

        return binding.root
    }

    private fun onPlayAgain() {

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScoreFragment().apply {

            }
    }
}