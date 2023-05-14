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
        //initialize button for trackServeButton and prevMatchButton
        val trackServeButton = findViewById<Button>(R.id.btnTrackServes)
        val prevMatchButton = findViewById<Button>(R.id.btnPrevMatches)
        newMatchButton.setOnClickListener{
            //when clicked, pull up the NameInput frag
            val Intent = Intent(this, NewMatch::class.java)
            startActivity(Intent)
        }

        //initialize playerComparison button
        val playerCompButton = findViewById<Button>(R.id.btnPlayerComp)
        playerCompButton.setOnClickListener{
            //when clicked, pull up player comparision page
            val Intent = Intent(this, PlayerCompActivity::class.java)
            startActivity(Intent)
        }

        trackServeButton.setOnClickListener{
            //pull up serve tracker page when button clicked
            val intent = Intent(this, ServeTracker::class.java)
            startActivity(intent)
        }

        prevMatchButton.setOnClickListener{
            //pull up previous matches page when button clicked
            val intent = Intent(this, PrevMatches::class.java)
            startActivity(intent)
        }

        }

    }
