package com.example.tennistracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

var totalpoints = 0
var P1totalpoints = 0
var P2totalpoints = 0


class NewMatch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)
        val scoreP1 = findViewById<TextView>(R.id.scoreP1)
        val scoreP2 = findViewById<TextView>(R.id.scoreP2)


        val P1Score = findViewById<Button>(R.id.btnP1Score)
        P1Score.setOnClickListener {
            totalpoints += 1
            P1totalpoints += 1
            scoreP1.text = "15 - "

        }
        val P2Score = findViewById<Button>(R.id.btnP2Score)
        P2Score.setOnClickListener {
            totalpoints += 1
            P2totalpoints += 1
            scoreP2.text = "15"
        }



    }
}