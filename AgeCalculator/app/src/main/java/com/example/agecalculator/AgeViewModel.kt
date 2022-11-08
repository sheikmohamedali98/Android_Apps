package com.example.agecalculator

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class AgeViewModel(private val context: Context) : ViewModel() {

    private val _selectDate = MutableLiveData<String>()
    val selectDate: LiveData<String>
        get() = _selectDate


    private val _minutes = MutableLiveData<String>()
    val minutes: LiveData<String>
        get() = _minutes

    init {
        _selectDate.value = ""
        _minutes.value = ""
    }


    fun clickPicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dataPick =
            DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                _selectDate.value = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = simpleDateFormat.parse(selectedDate)

                date?.let {

                    val selectDateInMinutes = date.time / 60000
                    val currentDate =
                        simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differntMinutes = currentDateInMinutes - selectDateInMinutes
                        _minutes.value = differntMinutes.toString()

                    }

                }


            }, year, month, day)
         dataPick.datePicker.maxDate = System.currentTimeMillis() -86400000

        dataPick.show()
    }

}