package com.example.tennistracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tennistracker.R
class MainActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //This gets button id, when we click on new match button we go to activity, NewMatch
        val newMatchButton = findViewById<Button>(R.id.btnNewMatch)
        val trackServeButton = findViewById<Button>(R.id.btnTrackServes)
        newMatchButton.setOnClickListener{
            val Intent = Intent(this, NewMatch::class.java)
            startActivity(Intent)
        }
        trackServeButton.setOnClickListener{
            val intent = Intent(this, ServeTracker::class.java)
            startActivity(intent)
        }
        }
    }
