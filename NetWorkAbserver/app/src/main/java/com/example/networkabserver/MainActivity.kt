package com.example.networkabserver

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.networkabserver.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectivityObserver = NetworkConnectivityObsever(applicationContext)
        connectivityObserver.observer().onEach {
            when(it.toString()){
                "Available"->{

                    binding.animation.setAnimation(R.raw.network_connections)
                    binding.animation.playAnimation()
                }
                "Unavailable"->{

                }
                "Losing"->{

                }
                else -> { binding.animation.setAnimation(R.raw.lost_connection)
                    binding.animation.playAnimation()}
            }
            binding.text.text = it.toString()
        }.launchIn(lifecycleScope)
    }
}