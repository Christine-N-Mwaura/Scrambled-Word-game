package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    //add backing property to variable score
    private var _score = 0
    val score: Int
        get() = _score

    private var currentWordCount = 0
    //Backing property implementation
    //make currentScramblesWord accessible and editable within GameViewModel class
    private lateinit var _currentScrambledWord: String

    //make the currentScrambled variable public and immutable
    val currentScrambledWord: String
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
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }

    }

    //method to increase score
    private fun increaseScore(){
        _score += SCORE_INCREASE
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
        return if (currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false
    }




    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }


}
