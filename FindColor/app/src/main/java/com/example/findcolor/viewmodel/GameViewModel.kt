package com.example.findcolor.viewmodel

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class GameViewModel:ViewModel() {
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    val timer:CountDownTimer
    private  val _currentTime = MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

   private val _word = MutableLiveData<String>()
    val word:LiveData<String>
    get() = _word

   private  val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished:LiveData<Boolean>
    get() = _eventGameFinished

    private lateinit var  wordList:MutableList<String>

    init{
        _eventGameFinished.value = false
        wordCollection()
        nextWord()
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
                _currentTime.value = DONE
                _eventGameFinished.value = true
            }
        }
        timer.start()
    }

    private fun wordCollection(){

        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )

        wordList.shuffle()
    }
    private fun nextWord(){
        if(wordList.isEmpty()){
////            gameFinished()
//            _eventGameFinished.value = true
            wordCollection()
        }
           _word.value = wordList.removeAt(0)
    }

     fun onSkip(){
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect(){
         _score.value = (score.value)?.plus(1)
        nextWord()
    }
    fun onGameFinished(){
        _eventGameFinished.value = false
    }
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}