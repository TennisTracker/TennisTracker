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
    var P1index = 1
    var P2index = 1
    var P1gameIndex = 0
    var P2gameIndex = 0
    var P1tiebreakScore = 0
    var P2tiebreakScore = 0
    var setIndexP1 = 0
    var setIndexP2 = 0
    var P1TotalFirstServeMissed = 0
    var P2TotalFirstServeMissed = 0
    var P1TotalSecondServeMissed = 0
    var P2TotalSecondServeMissed = 0
    var P1TotalReturnMissed = 0
    var P2TotalReturnMissed = 0
    var P1ServesTotal = 0
    var P2ServesTotal = 0
    var FirstServer = 0
    var P1FirstServesHit = 0
    var P2FirstServesHit = 0


}