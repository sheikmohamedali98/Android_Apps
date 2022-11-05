package com.example.camerax2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//                requestWindowFeature(Window.FEATURE_NO_TITLE)
//                this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)



    }

}