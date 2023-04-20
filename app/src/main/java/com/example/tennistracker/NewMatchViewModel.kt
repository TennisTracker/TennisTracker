package com.example.tennistracker


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class NewMatchViewModel(private  val savedStateHandle: SavedStateHandle): ViewModel(){

    var p1Name = ""
    var p2Name = ""

    var totalpoints = 0
    var P1totalpoints = 0
    var P2totalpoints = 0
    var P1index = 0
    var P2index = 0
    var P1gameIndex = 0
    var P2gameIndex = 0
    var P1tiebreakScore = 0
    var P2tiebreakScore = 0
    var setIndexP1 = 0
    var setIndexP2 = 0


}