package com.example.timeconvertor

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import com.example.timeconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.timePicker.setOnClickListener {
            var time:String = binding.editText.text.toString()
           if(time.isNotEmpty()){
               val  findAMorPm:String = time.substring(time.length-2,time.length)
               if(findAMorPm.equals("pm")){
                  time =  time.replace("pm","hrs")
                   println("\n\n\n\n${time}\n\n\n")
                   val stringArray = time.split(":")
                   val list = mutableListOf<String>()
                   for(string in stringArray){
                       list.add(string)
                   }
                   var number =  stringArray[0].toInt().plus(12)
                   if(number>23){
                       number = 0
                   }

                   list.add(0,number.toString())
                   list.removeAt(1)
                   binding.textView.text = list.toString()

               }else {
                   binding.textView.text = time
               }
               println(findAMorPm)
           }else{
               Toast.makeText(this, "Enter Valid input", Toast.LENGTH_SHORT).show()
           }
        }

        binding.next.setOnClickListener {
            val intent = Intent(this,SecondActivity2::class.java)
            startActivity(intent)
        }
    }
}