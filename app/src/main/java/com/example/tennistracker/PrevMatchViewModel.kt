package com.example.tennistracker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class PrevMatchViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(){
    var pageIndex = 0
    var numMatches = 0
    var prevMatchIndex = 0
    var P1totalpoints = 0.0
    var P2totalpoints = 0.0
    var P1TotalFirstServeMissed = 0.0
    var P2TotalFirstServeMissed = 0.0
    var P1TotalSecondServeMissed = 0.0
    var P2TotalSecondServeMissed = 0.0
    var P1TotalReturnMissed = 0.0
    var P2TotalReturnMissed = 0.0
    var P1ServesTotal = 0.0
    var P2ServesTotal = 0.0
}