package com.example.calculatorapp.viewmodel

class CalculatingValues {
    companion object{

        fun addtion(result: Int, tempString: String): Int {
            var result = result
            return tempString.toInt().let { result += it; result }
        }

        fun subtraction(result: Int, tempString: String): Int {
            var result = result
            return tempString.toInt().let { result -= it; result }
        }

        fun multiplication(result: Int, tempString: String): Int {
            var result = result
            return tempString.toInt().let { result *= it; result }
        }

        fun division(result: Int, tempString: String): Int {
            var result = result
            return tempString.toInt().let { result /= it; result }
        }

        fun operationPerformedBy(calculateNumberValue:Int, currentChar: Char, tempString: String? ): Int =
            when (currentChar) {
                '+' ->  addtion(calculateNumberValue, tempString.toString())
                '-' -> subtraction(calculateNumberValue, tempString.toString())
                '*' -> multiplication(calculateNumberValue, tempString.toString())
                '/' -> division(calculateNumberValue, tempString.toString())
                 else -> 0
        }

        }
    }
