package com.example.tennistracker

data class Match(
    val p1name : String? = null,
    val p1pointsWon : Double,
    val p1totalFirstServeMissed: Double,
    val p1totalSecondServeMissed: Double,
    val p1totalReturnMissed: Double,
    val p1servesTotal: Double,
    val p2name : String? = null,
    val p2pointsWon : Double,
    val p2totalFirstServeMissed: Double,
    val p2totalSecondServeMissed: Double,
    val p2totalReturnMissed: Double,
    val p2servesTotal: Double,
    val dateAndTime: String,
)
