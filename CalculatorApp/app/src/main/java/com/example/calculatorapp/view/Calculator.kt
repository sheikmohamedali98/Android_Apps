package com.example.calculatorapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculatorapp.R
import com.example.calculatorapp.databinding.FragmentCalCulatorBinding
import com.example.calculatorapp.viewmodel.CalculatorViewModel


class Calculator : Fragment() {

    lateinit var binding:FragmentCalCulatorBinding
    lateinit var  viewModel: CalculatorViewModel
    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false

    // If true, do not allow to add another DOT
    var lastDot: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cal_culator,container,false)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.display.observe(viewLifecycleOwner, Observer {
            binding.didplay.text = it
        })
        viewModel.result.observe(viewLifecycleOwner, Observer {
            binding.resultView.text = it.toString()
        })
        return binding.root
    }


    fun onDecimalPoint(view: View) {
        // If the last appended value is numeric then append(".") or don't.
        if (lastNumeric && !lastDot) {
            binding.didplay?.append(".")
            lastNumeric = false // Update the flag
            lastDot = true // Update the flag
        }
    }

    fun onDigit(view: View){
        binding.didplay?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        binding.didplay?.text = ""
        binding.totalClearBtn?.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onCancelLast(view: View) {
        val length = binding.didplay.length()?:0
        if (length != 0) {
            binding.didplay.text = binding.didplay.text.toString().substring(0, length.minus(1))
        }
    }


}


