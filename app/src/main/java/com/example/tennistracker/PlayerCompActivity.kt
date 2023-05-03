package com.example.tennistracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tennistracker.databinding.ActivityPlayerCompBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.round

private lateinit var  database : DatabaseReference
private lateinit var binding: ActivityPlayerCompBinding


class PlayerCompActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerCompBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Create database reference
        database = FirebaseDatabase.getInstance().getReference("Players")

        var p1name = ""
        var p2name = ""
        binding.updateButton.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("Players")
            //Get P1 name and P2 name
            p1name = binding.etP1Name.text.toString()
            p2name = binding.etP2Name.text.toString()

            //Search to see if p1 name in database if so then return stats
            database.child(p1name).get().addOnSuccessListener{
                if(it.exists()){
                    val newPointsWon = it.child("pointsWon").getValue(Double::class.java)!!
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Double::class.java)!!
                    val newFirstServeMissed = it.child("totalFirstServeMissed").getValue(Double::class.java)!!
                    val newSecondServeMissed = it.child("totalSecondServeMissed").getValue(Double::class.java)!!
                    val newTotalServeMissed = it.child("totalReturnMissed").getValue(Double::class.java)!!
                    val newServesTotal = it.child("servesTotal").getValue(Double::class.java)!!

                    binding.txtP1PointsWon.text = newPointsWon.toString()
                    binding.txtP1PointsPlayed.text = round((newPointsWon * 100 / newPointsPlayed)).toString()
                    binding.txtP1TotalFirstServeMissed.text = round((newServesTotal - newFirstServeMissed) * 100/ newServesTotal).toString()
                    binding.txtP1TotalSecondServeMissed.text = round((newFirstServeMissed - newSecondServeMissed) * 100/ newFirstServeMissed).toString()
                    binding.txtP1ServesTotal.text = newServesTotal.toString()
                    binding.txtP1TotalReturnMissed.text = round((newServesTotal - newFirstServeMissed - newSecondServeMissed - newTotalServeMissed) * 100 / (newServesTotal - newFirstServeMissed - newSecondServeMissed)).toString()
                }
                else{
                    Toast.makeText(this, "P1 Name was not entered!", Toast.LENGTH_SHORT).show()
                }
            }

            //Search to see if p2 name in database if so then return stats
            database.child(p2name).get().addOnSuccessListener{
                if(it.exists()){
                    val newPointsWon = it.child("pointsWon").getValue(Double::class.java)!!
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Double::class.java)!!
                    val newFirstServeMissed = it.child("totalFirstServeMissed").getValue(Double::class.java)!!
                    val newSecondServeMissed = it.child("totalSecondServeMissed").getValue(Double::class.java)!!
                    val newTotalServeMissed = it.child("totalReturnMissed").getValue(Double::class.java)!!
                    val newServesTotal = it.child("servesTotal").getValue(Double::class.java)!!

                    binding.txtP2PointsWon.text = newPointsWon.toString()
                    binding.txtP2PointsPlayed.text = round(newPointsWon * 100 / newPointsPlayed).toString()
                    binding.txtP2TotalFirstServeMissed.text = round((newServesTotal - newFirstServeMissed) * 100/ newServesTotal).toString()
                    binding.txtP2TotalSecondServeMissed.text = round((newFirstServeMissed - newSecondServeMissed) * 100/ newFirstServeMissed).toString()
                    binding.txtP2ServesTotal.text = newServesTotal.toString()
                    binding.txtP2TotalReturnMissed.text = round((newServesTotal - newFirstServeMissed - newSecondServeMissed - newTotalServeMissed) * 100 / (newServesTotal - newFirstServeMissed - newSecondServeMissed)).toString()
                }
                else{
                    Toast.makeText(this, "P2 Name was not entered!", Toast.LENGTH_SHORT).show()
                }
            }


        }



    }
}