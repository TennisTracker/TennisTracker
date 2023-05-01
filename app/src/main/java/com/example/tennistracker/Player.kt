package com.example.tennistracker

data class Player(
    val name : String? = null,
    val pointsWon : Int,
    val pointsPlayed : Int,
    val totalFirstServeMissed: Int,
    val totalSecondServeMissed: Int,
    val totalReturnMissed: Int,
    val servesTotal: Int


)
