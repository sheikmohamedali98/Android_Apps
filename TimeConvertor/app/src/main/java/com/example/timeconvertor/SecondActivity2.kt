package com.example.timeconvertor

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.timeconvertor.databinding.ActivitySecond2Binding
import java.util.*

class SecondActivity2 : AppCompatActivity() {
    private  lateinit var  binding: ActivitySecond2Binding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecond2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val simpleDate = SimpleDateFormat("dd/M/yyyy HH:mm:ss")
        val currentDate = simpleDate.format(Date())
        println(" Current Date is: " +currentDate)
        val splitDateAndTime = currentDate.split(" ")
        binding.button.setOnClickListener {

            val currentDate  = splitDateAndTime[0]
            val currentDateArray = currentDate.split("/")
            val currentTime = splitDateAndTime[1]
            val currentTimeArray = currentTime.split(":")
            val userDate  = binding.date.text.toString()


            val userDateArray = userDate.split("/")
            val userTime = binding.time.text.toString()
            val userTimeArray = userTime.split(":")
            if(currentDate.equals(userDate)){

                if(currentTime.equals(userTime)) {
                    binding.answer.text = "now"
                }else if(currentTimeArray[0].equals(userTimeArray[0])){
                    val minutes = currentTimeArray[1].toInt() - userTimeArray[1].toInt()
                    binding.answer.text = minutes.toString()+" minutes"
                }else if(currentTimeArray[0].equals(userTimeArray[0])&&currentTimeArray[1].equals(userTimeArray[1])){
                    val seconds = currentTimeArray[2].toInt() - userTimeArray[2].toInt()
                    binding.answer.text = seconds.toString()+" seconds"
                }else if(!currentTimeArray[0].equals(userTimeArray[0])){
                    val hours = currentTimeArray[0].toInt() - userTimeArray[0].toInt()
                    binding.answer.text = hours.toString()+" hours"
                }else{
                    binding.answer.text = "now"

                }
//                Toast.makeText(this, "fai", Toast.LENGTH_SHORT).show()
            }
//
//            else if(currentDate.equals(userDate)){
//                if(userTimeArray[0].equals(currentTimeArray[0])){
//                    val number = userTimeArray[1].toInt() - currentTimeArray[1].toInt()
//                    binding.answer.text =  number.toString()+" minutes"
//                }else if(userTimeArray[1].equals(currentTimeArray[1])){
//                    val number = userTimeArray[2].toInt() - currentTimeArray[2].toInt()
//                    binding.answer.text =  number.toString()+" seconds"
//
//                }else if(userTimeArray[2].equals(currentTimeArray[2])){
//                    val number = userTimeArray[2].toInt() - currentTimeArray[2].toInt()
//                    binding.answer.text =  number.toString()+" seconds"
//                }else{
//                    binding.answer.text = "now"
//
//                }
//
//
//            }
            else{
//                if(currentTimeArray[2].equals(userTimeArray[2])&&currentTimeArray[1].equals(currentTimeArray[1])){
//                    binding.answer.text = "now"
//                }else if(currentTimeArray[1].equals(currentTimeArray[1])&&currentTimeArray[0].equals(currentTimeArray[0])){
//                    binding.answer.text = ( currentTimeArray[2].toInt() - userTimeArray[2].toInt()).toString()+"minutes"
//                }else{
//                    binding.answer.text = ( currentTimeArray[1].toInt() - userTimeArray[1].toInt()).toString()+"hours"
//
//                }
                if(userDateArray[2].equals(currentDateArray[2])&&userDateArray[1].equals(currentDateArray[1])){
                    var  days =  currentDateArray[0].toInt() - userDateArray[0].toInt()

                    if(days>7){
                        val weeks = days/7
                        days = days%7
                        binding.answer.text = weeks.toString()+"  weeks" +" "+days.toString()+ " days"
                    }else{
                        binding.answer.text = days.toString()+" days"
                    }
                }

                }
        }


    }
}