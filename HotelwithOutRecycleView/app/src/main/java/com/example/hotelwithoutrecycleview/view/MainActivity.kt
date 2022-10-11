package com.example.hotelwithoutrecycleview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hotelwithoutrecycleview.R
import com.example.hotelwithoutrecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}