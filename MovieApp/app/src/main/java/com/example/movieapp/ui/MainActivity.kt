package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val bottomNavigate = binding.bottomNavigate
//        val navigate = findNavController(R.id.fragmentContainerView)
//        bottomNavigate.setupWithNavController()
        installSplashScreen()
        val navController= Navigation.findNavController(this,R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNavigate,navController)
//        bottomNavigate.setupWithNavController(navController)


    }
}