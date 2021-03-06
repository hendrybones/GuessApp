package com.example.guessapp.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    companion object{
        // these represent important tines of the game
        private const val DONE=0L
        private const val ONE_SECOND=1000L
        // total time of the game
        private const val COUNTDOWN_TIME=10000L
    }
     private val timer: CountDownTimer



    // The current word
   val _word = MutableLiveData<String>()
    // live data is public but not mutable cannot be modified
    val word: LiveData<String>
        get() =_word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() =_score

    private val _eventGameFinished= MutableLiveData<Boolean>()
    val  eventGameFinished: LiveData<Boolean>
        get() =_eventGameFinished

    private val _currentTime=MutableLiveData<Long>()
    val currentTime:LiveData<Long>
        get() =_currentTime

    //     The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel","GameViewModel created")
        _eventGameFinished.value=false
        resetList()
        nextWord()
        _score.value=0
        _word.value=""
        //creates a timer which triggers the end game when it is finished
        timer= object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value=(millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value= DONE
                _eventGameFinished.value=true
            }

        }
        timer.start()
        //you will need DateUtils..formatElapsedTime()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel destroyed")
        timer.cancel()

    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
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
    private fun nextWord() {
        if (wordList.isEmpty()){
            resetList()
        }
        _word.value= wordList.removeAt(0)
/*
//Select and remove a word from the list
if (wordList.isEmpty()) {
_eventGameFinished.value=true
} else {
_word.value= wordList.removeAt(0)
}
updateWordText()
updateScoreText()
*/
    }
   fun onSkip() {
        _score.value=(score.value)?.minus(1)
        nextWord()
    }

   fun onCorrect() {
       _score.value=(score.value)?.plus(1)
        nextWord()
    }
    fun onGameFinishComplete(){
        _eventGameFinished.value=false
    }


}