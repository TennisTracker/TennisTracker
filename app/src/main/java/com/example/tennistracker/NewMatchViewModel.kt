package com.example.tennistracker


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class NewMatchViewModel(private  val savedStateHandle: SavedStateHandle): ViewModel(){

    var p1Name = ""
    var p2Name = ""

    //view model for necessary variables of interest
    var totalpoints = 0.0
    var P1totalpoints = 0.0
    var P2totalpoints = 0.0

    //p1index and p2index keep track of the current game score within set
    var P1index = 1
    var P2index = 1

    //p1gameIndex keeps track of current game score within game
    var P1gameIndex = 0
    var P2gameIndex = 0

    //track tiebreak score
    var P1tiebreakScore = 0
    var P2tiebreakScore = 0

    //track match total sets won
    var setIndexP1 = 0
    var setIndexP2 = 0

    //track total first serve missed, total second serve missed, and total return missed for both players
    var P1TotalFirstServeMissed = 0.0
    var P2TotalFirstServeMissed = 0.0
    var P1TotalSecondServeMissed = 0.0
    var P2TotalSecondServeMissed = 0.0
    var P1TotalReturnMissed = 0.0
    var P2TotalReturnMissed = 0.0

    //track total serves hit for both players
    var P1ServesTotal = 0.0
    var P2ServesTotal = 0.0
    //track who the current server is
    var FirstServer = 0
    var FirstServeMissBool = false

    //track what the set scores ended up being
    var Set1P1 = "0"
    var Set1P2 = "0"
    var Set2P1 = "0"
    var Set2P2 = "0"
    var Set3P1 = "0"
    var Set3P2 = "0"


}