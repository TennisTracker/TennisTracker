package com.example.tennistracker

data class Player(
    val name : String? = null,
    val pointsWon : Double,
    val pointsPlayed : Double,
    val totalFirstServeMissed: Double,
    val totalSecondServeMissed: Double,
    val totalReturnMissed: Double,
    val servesTotal: Double


)
