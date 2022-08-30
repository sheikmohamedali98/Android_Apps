package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightField = findViewById<EditText>(R.id.lblWeight)
        val heightField = findViewById<EditText>(R.id.lblHeight)
        val result = findViewById<TextView>(R.id.lblResult)
        val btCalculate = findViewById<Button>(R.id.btCalculate)
        var inWeight = ""
        var inHeight = ""
        var message = ""
        btCalculate.setOnClickListener {
            inWeight = weightField.text.toString()
            inHeight = heightField.text.toString()

            if (inWeight != null && inHeight != null && !"".equals(inHeight) && !"".equals(inWeight)) {

                var height = inHeight.toFloat()
                var weight = inWeight.toFloat()
                var bmi = (weight/(height*height))*10000

                weightField.text.clear()
                heightField.text.clear()



                if(bmi <=18.5){
                    message = "Your BMI Is $bmi \n  Your Under Weight\n\t Have a Nice day"

                }else if (bmi >18.5&&bmi<=24.99){
                    message = "Your BMI Is $bmi \n  Your Normal  Weight\n\t Have a Nice day"

                }else if (bmi >25&&bmi<=29.99) {
                    message = "Your BMI Is $bmi \n  Your Heavy  Weight\n\t Have a Nice day"
                }else{
                    message = "Your BMI Is $bmi \n  Your Obesity \n\t Have a Nice day"

                }
                result.text = message
                result.visibility = View.VISIBLE


            }


        }


    }

}