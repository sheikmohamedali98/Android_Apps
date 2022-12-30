package com.example.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.flow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel:FlowViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(this)[FlowViewmodel::class.java]

        binding.stateBtn.setOnClickListener {
            viewmodel.updateState(binding.editTextTextPersonName.text.toString())
        }

        binding.shareBtn.setOnClickListener {
            viewmodel.updateShare(binding.editTextTextPersonName.text.toString())
        }

        lifecycleScope.launchWhenStarted {
            viewmodel.stateFlow.collectLatest {
                binding.stateFlow.text = it
                Toast.makeText(this@MainActivity, "State Flow ", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewmodel.sharedFlow.collectLatest {
                binding.textView.text = it
                Snackbar.make(binding.root,"Share Flow ",Snackbar.LENGTH_LONG).show()

            }
        }
    }
}