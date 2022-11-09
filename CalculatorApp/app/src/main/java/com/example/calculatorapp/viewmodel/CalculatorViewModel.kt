package com.example.calculatorapp.viewmodel


import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel : ViewModel() {


    private val _display = MutableLiveData<String>()
    val display: LiveData<String>
        get() = _display

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result


    init {
        _display.value = ""
        _result.value = ""

    }

    fun clearScreen() {
        _display.value = ""
        _result.value = ""

    }

//    fun onDigit(){
//     _display.value =.append(.text)
//        lastNumeric = true
//    }
    fun calcelLast() {
        val length = _display.value?.length?:0
        if (length != 0) {
            _display.value = _display.value?.substring(0, length.minus(1))
        }
    }


    fun onEqual() {
        // If the last input is a number only, solution can be found.
        if (_display.value?.get(_display?.value!!.length-1)?.isDigit() == true) {
            // Read the textView value
            var tvValue = _display.value
            var prefix = ""
            try {

                // Here if the value starts with '-' then we will separate it and perform the calculation with value.
                if (tvValue?.startsWith("-") == true) {
                    prefix = "-"
                    tvValue = tvValue.substring(1);
                }

                // If the inputValue contains the Division operator
                if (tvValue != null) {
                    when {
                        tvValue.contains("/") -> {
                            // Will split the inputValue using Division operator
                            val splitedValue = tvValue.split("/")

                            var one = splitedValue[0] // Value One
                            val two = splitedValue[1] // Value Two

                            if (prefix.isNotEmpty()) {
                                one = prefix + one
                            }


                            _result.value= removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                        }
                        tvValue.contains("*") -> {
                            // If the inputValue contains the Multiplication operator
                            // Will split the inputValue using Multiplication operator
                            val splitedValue = tvValue.split("*")

                            var one = splitedValue[0] // Value One
                            val two = splitedValue[1] // Value Two

                            if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                                one = prefix + one
                            }

                            _result.value = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                        }
                        tvValue.contains("-") -> {

                            // If the inputValue contains the Subtraction operator
                            // Will split the inputValue using Subtraction operator
                            val splitedValue = tvValue.split("-")

                            var one = splitedValue[0] // Value One
                            val two = splitedValue[1] // Value Two

                            if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                                one = prefix + one
                            }

                            _result.value = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                        }
                        tvValue.contains("+") -> {
                            // If the inputValue contains the Addition operator
                            // Will split the inputValue using Addition operator
                            val splitedValue = tvValue.split("+")

                            var one = splitedValue[0] // Value One
                            val two = splitedValue[1] // Value Two

                            if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                                one = prefix + one
                            }

                            _result.value = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                        }
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }


    fun operation(){
        var value = _display.value
        var result = "0"
        if (value != null) {
            value = value.replace("÷","/")
            value = value.replace("×","*")
            val exp = Expression(value)
             result = exp.expressionString

        }
        println("\n\n\n\n${result}\n\n\n\n")

        _result.value = result
    }

    fun appendOperation(ch: Char,canAdd:Boolean) {
        var word = ch.toString()
       if(canAdd){
           _result.value = ""
           _display.value += word
       }else{
           _display.value += _result.value
           _display.value += word
           _result.value = ""
       }
    }

    fun dotExpression(){
        if(_display.value?.isEmpty() == true){
            return
        }
        if(_display.value?.last()?.isDigit() != true){
            return
        }
         appendOperation('.',true)
    }

//    fun addDotToExpression() {
//        if(expression.value!!.isEmpty()) return
//        if(!expression.value!!.last().isDigit()) return
//        addToExpression(".")
//    }

//    binding.cBtn.setOnClickListener {
//            var number = showText.text.toString()
//            if (number.isNotEmpty()) {
//                showText.text = number.substring(0, number.length - 1)
//            } else {
//                showText.text = ""
//            }
//        }

//    private fun validateTheNumber(numberString: String) {
//        var tempString = ""
//        var currentChar = ' '
//        var tempChar = ' '
//        var result = 0
//        for (position in 0 until numberString.length) {
//            if (numberString[position] == '+' || numberString[position] == '-' || numberString[position] == '*' || numberString[position] == '/' ) {
//                if (numberString[position] != '=') {
//                    tempChar = numberString[position]
//                }
//                if (_result.value?.isEmpty() == true) {
//                    result = tempString.toInt()
//                    _result.value = result.toString()
//                    currentChar = numberString[position]
//                    tempString = ""
//                } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
//                    _display.value = CalculatingValues
//                        .operationPerformedBy(_result.value?.toInt()!!,
//                            currentChar,
//                            tempString).toString()
//                    currentChar = tempChar
//                    tempString = ""
//                }
//                } else if (numberString[position].code >= 48 && numberString[position].code <= 57) {
//                    tempString += numberString[position]
//                } else {
////                    Toast.makeText(this@CalculatorViewModel, "Invalid INPUT ", Toast.LENGTH_SHORT)
//                    break
//                }
//            }
//        }
    }
