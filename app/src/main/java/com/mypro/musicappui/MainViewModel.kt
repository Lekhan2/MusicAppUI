package com.mypro.musicappui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerSceen.AddAcount)

    val currentScreen : MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
            _currentScreen.value=screen
    }

}