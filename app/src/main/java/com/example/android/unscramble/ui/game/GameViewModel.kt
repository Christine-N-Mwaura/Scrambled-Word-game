package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var score = 0
    private var currentWordCount = 0

    //Backing property implementation
    //make currentScramblesWord accessible and editable within GameViewModel class
    private var _currentScrambledWord = "test"

    //make the currentScrambled variable public and immutable
    val currentScrambledWord: String
        get() = _currentScrambledWord
}