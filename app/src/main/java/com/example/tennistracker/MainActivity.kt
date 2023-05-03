package com.example.tennistracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //This gets button id, when we click on new match button we go to activity, NewMatch
        val newMatchButton = findViewById<Button>(R.id.btnNewMatch)
        newMatchButton.setOnClickListener{
            val Intent = Intent(this, NewMatch::class.java)
            startActivity(Intent)
        }
        val playerCompButton = findViewById<Button>(R.id.btnPlayerComp)
        playerCompButton.setOnClickListener{
            val Intent = Intent(this, PlayerCompActivity::class.java)
            startActivity(Intent)
        }

    }
}