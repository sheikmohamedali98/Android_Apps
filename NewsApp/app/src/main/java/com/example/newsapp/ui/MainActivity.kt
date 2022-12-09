package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.newspaper_a)
        supportActionBar?.subtitle = "welcome"
//        val navController = this.findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.over_flow_menu,menu)
//        return true
//
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }
}