package com.example.alarammanager

import androidx.lifecycle.ViewModel
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

class AlarmManagerViewModel:ViewModel() {
    private val _time = MutableStateFlow("00 : 00 Am")
    val time:StateFlow<String> = _time
    val calendar: Calendar = Calendar.getInstance()


    fun timePicker(timeFormate:Boolean):MaterialTimePicker{
        val timeFormater = if(timeFormate) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
         val picker = MaterialTimePicker.Builder()
            .setTimeFormat(timeFormater)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.addOnPositiveButtonClickListener {
            _time.value = String.format("%02d",picker.hour)+":"+String.format("%02d",picker.minute)
            calendar.set(Calendar.HOUR_OF_DAY,picker.hour)
            calendar.set(Calendar.MINUTE,picker.minute)
            calendar.set(Calendar.SECOND,0)
            calendar.set(Calendar.MILLISECOND,0)
        }
        

        return picker
    }
}