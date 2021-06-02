package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    //add backing property to variable score
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    //Backing property implementation
    //make currentScramblesWord accessible and editable within GameViewModel class
    private val _currentScrambledWord = MutableLiveData<String>()


    //make the currentScrambled variable public and immutable
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        //Use while loop to make sure the current word is not the same as the shuffled word
        while (tempWord.toString().equals(currentWord,false)){
            tempWord.shuffle()
        }

        if(wordsList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }

    }

    //method to increase score
    private fun increaseScore(){
        _score.value =(_score.value)?.plus(SCORE_INCREASE)
    }

    //check if the word entered is correct
    fun isUserWordCorrect(playerWord: String): Boolean{
        if (playerWord.equals(currentWord,true)){
            increaseScore()
            return true
        }
        return false
    }
    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false
    }


    /*
    * Re-initializes the game data to restart the game.
    */
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }


}
