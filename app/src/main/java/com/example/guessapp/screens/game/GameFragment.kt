package com.example.guessapp.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.guessapp.GameViewModel
import com.example.guessapp.R
import com.example.guessapp.databinding.FragmentGameBinding
import com.example.guessapp.screens.score.ScoreFragment


class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        Log.i("GameViewModel","called viewModelProviders.of!")
        viewModel= ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
            }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })
        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished){
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })
        return binding.root

    }
    /**
     * Called when the game is finished
     */
   private fun gameFinished(){
//       val action=GameFragmentDirections.actionGameFragmentToScoreFragment()
//        val currentScore=viewModel.score.value?:0
//        action.setScore(currentScore)
//        findNavController(this).navigate(action)
       Toast.makeText(this.activity,"Game has finished",Toast.LENGTH_SHORT).show()
   }
    /**
     * Moves to the next word in the list
     */
    /** Methods for buttons presses **/



    /** Methods for updating the UI **/

}