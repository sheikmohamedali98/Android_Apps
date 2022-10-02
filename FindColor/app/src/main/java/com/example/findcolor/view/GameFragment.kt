package com.example.findcolor.view

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.findcolor.R
import com.example.findcolor.databinding.FragmentGameBinding
import com.example.findcolor.viewmodel.GameViewModel

class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
//        binding.correctButton.setOnClickListener {
//            viewModel.onCorrect()
//
//        }
        binding.gameViewModel = viewModel
//        binding.skipButton.setOnClickListener {
//            viewModel.onSkip()
//
//        }
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })

        binding.setLifecycleOwner(this)
//        viewModel.word.observe(viewLifecycleOwner, Observer { newWord->
//            binding.wordText.text = newWord
//        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)

        })

        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController().navigate(action)
                viewModel.onGameFinished()
            }
        })

        return binding.root

    }

//  private fun gameFinished(){
////      var action = GameFragmentDirections.actionGameToScore(viewModel.score.value?:0)
//////      var currentScore = viewModel.score.value?:0
////      findNavController().navigate(action)
//      Toast.makeText(this.activity,"game Finished",Toast.LENGTH_SHORT).show()
//  }



}