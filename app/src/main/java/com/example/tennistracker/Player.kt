package com.example.tennistracker

import androidx.room.Entity
import androidx.room.PrimaryKey

//This data class control the variables that we track in DB
@Entity
data class Player(
    val firstName: String,
    val lastName: String,
    val points: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0
)
