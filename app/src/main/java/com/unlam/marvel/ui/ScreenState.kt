package com.unlam.marvel.ui

import com.unlam.marvel.data.Character

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<Character>) : ScreenState()
}