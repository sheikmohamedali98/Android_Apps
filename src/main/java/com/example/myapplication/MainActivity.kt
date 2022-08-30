package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greetingTextView = findViewById<TextView>(R.id.tvHello)
        val inputField = findViewById<EditText>(R.id.etName)
        val buttonField = findViewById<Button>(R.id.tvButton)
        val offerButton = findViewById<Button>(R.id.btnOffer)
        var enteredName = ""
        buttonField.setOnClickListener {
              enteredName = inputField.text.toString()
            if(enteredName == ""){
                offerButton.visibility = INVISIBLE
                greetingTextView.text = ""

                Toast.makeText(this@MainActivity,"Please Enter Your name",Toast.LENGTH_SHORT).show()
            }else {
                val message = "Welcome $enteredName\n\t Have a Nice day"
                greetingTextView.text = message
                inputField.text.clear()
                buttonField.visibility = INVISIBLE
                inputField.visibility = INVISIBLE
                offerButton.visibility = VISIBLE
            }
        }

        offerButton.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("USER",enteredName)
            startActivity(intent)
        }

    }
}