package com.example.tennistracker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class PrevMatchViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(){
    var pageIndex = 0
    var numMatches = 0
}