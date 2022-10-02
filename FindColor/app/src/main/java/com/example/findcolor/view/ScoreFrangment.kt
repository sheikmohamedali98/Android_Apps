package com.example.findcolor.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.findcolor.R
import com.example.findcolor.databinding.FragmentScoreFrangmentBinding
import com.example.findcolor.viewmodel.ScoreViewModel
import com.example.findcolor.viewmodel.ScoreViewModelFactory




class ScoreFrangment : Fragment() {

    lateinit var viewModel: ScoreViewModel
    lateinit var viewModelFactory: ScoreViewModelFactory
    lateinit var  binding: FragmentScoreFrangmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.id.score_layout,container,false)


//        val score = requireArguments().get("score")
//        viewModelFactory = ScoreViewModelFactory(ScoreFrangmentArgs.fromBundle(score as Bundle).score.toInt())
//        viewModel = ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)



        // Get args using by navArgs property delegate
      val scoreFrangmentArgs by navArgs<ScoreFrangmentArgs>()
//        binding.scoreText.text = scoreFrangmentArgs.score.toString()

        viewModelFactory = ScoreViewModelFactory(scoreFrangmentArgs.score)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)

        binding.sc

//        binding.scoreText.text = ScoreFrangmentArgs.fromBundle(score as Bundle).score.toString()
//        binding.playAgainButton.setOnClickListener { onPlayAgain() }

//        :Argument by navArgs<>()
        //get args from
//        val score = requireArguments().get("score")
//        Toast.makeText(activity, "score : ${score}",Toast.LENGTH_SHORT).show()

        // Add observer for score
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })
        binding.setLifecycleOwner(this)

        // Navigates back to title when button is pressed
        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFrangmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })
        return binding.root
    }

}