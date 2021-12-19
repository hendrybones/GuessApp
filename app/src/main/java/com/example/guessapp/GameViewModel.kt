package com.example.guessapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {

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

    //    / The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel","GameViewModel created")
        _eventGameFinished.value=false
        resetList()
        nextWord()
        _score.value=0
        _word.value=""
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel destroyed")

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
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _eventGameFinished.value=true
        } else {
            _word.value= wordList.removeAt(0)
        }
//        updateWordText()
//        updateScoreText()
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