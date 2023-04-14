package com.example.tennistracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.LayoutInflater
import com.example.tennistracker.databinding.ActivityNewMatchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


var totalpoints = 0
var P1totalpoints = 0
var P2totalpoints = 0
var P1index = 0
var P2index = 0
var P1gameIndex = 0
var P2gameIndex = 0
var P1tiebreakScore = 0
var P2tiebreakScore = 0

//initalize array of possible game scores and set scores
val gameScores: IntArray = intArrayOf(0, 15, 30, 40)
val setScores: IntArray = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)

class NewMatch : AppCompatActivity() {
    private lateinit var binding: ActivityNewMatchBinding
    private lateinit var  database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scoreP1 = findViewById<TextView>(R.id.scoreP1)
        val scoreP2 = findViewById<TextView>(R.id.scoreP2)
        val gameP1 = findViewById<TextView>(R.id.gameP1)
        val gameP2 = findViewById<TextView>(R.id.gameP2)
        //initalize player index to 1 to combat onCreate already starting game score at 0
        P1index = 1
        P2index = 1



        binding.btnP1Score.setOnClickListener {
            totalpoints += 1
            P1totalpoints += 1

            if (P1index == 4) {
                P1index = 0
                P2index = 0
                P1gameIndex +=1
                gameP1.text = setScores[P1gameIndex].toString()
                scoreP2.text = gameScores[P2index].toString()
                P2index +=1
                scoreP1.text = gameScores[P1index].toString()
                P1index+=1

            }
            else if (setScores[P1gameIndex] == 6 && setScores[P2gameIndex] == 6){
                P1tiebreakScore +=1
                scoreP1.text = P1tiebreakScore.toString()
                if (P1tiebreakScore > 6 && (P1tiebreakScore - P2tiebreakScore) > 1){
                    P1gameIndex = 0
                    P2gameIndex = 0
                    P1index = 0
                    P2index = 0
                    gameP1.text = setScores[P1gameIndex].toString()
                    gameP2.text = setScores[P2gameIndex].toString()
                    scoreP1.text = gameScores[P1index].toString()
                    scoreP2.text = gameScores[P2index].toString()
                }
            }
            else if ((setScores[P1gameIndex] == 6 && setScores[P2gameIndex] < 5) || setScores[P1gameIndex] == 7 && setScores[P2gameIndex] < 6){
                P1gameIndex = 0
                P2gameIndex = 0
                gameP1.text = setScores[P1gameIndex].toString()
                gameP2.text = setScores[P2gameIndex].toString()
                scoreP1.text = gameScores[P1index].toString()
                P1index+=1
            }
            else{
                scoreP1.text = gameScores[P1index].toString()
                P1index+=1
            }


        }


        binding.btnP2Score.setOnClickListener {
            totalpoints += 1
            P2totalpoints += 1
            if (P2index == 4){
                P2index = 0
                P1index = 0
                P2gameIndex +=1

                gameP2.text = setScores[P2gameIndex].toString()
                scoreP1.text = gameScores[P1index].toString()
                P1index +=1
                scoreP2.text = gameScores[P2index].toString()
                P2index+=1

            }
            else if (setScores[P1gameIndex] == 6 && setScores[P2gameIndex] == 6){
                P2tiebreakScore +=1
                scoreP2.text = P2tiebreakScore.toString()
                if (P2tiebreakScore > 6 && (P2tiebreakScore - P1tiebreakScore) > 1){
                    P1gameIndex = 0
                    P2gameIndex = 0
                    P1index = 0
                    P2index = 0
                    gameP1.text = setScores[P1gameIndex].toString()
                    gameP2.text = setScores[P2gameIndex].toString()
                    scoreP1.text = gameScores[P1index].toString()
                    scoreP2.text = gameScores[P2index].toString()

                }
            }


            else if ((setScores[P2gameIndex] == 6 && setScores[P1gameIndex] < 5) || setScores[P2gameIndex] == 7 && setScores[P1gameIndex] < 6){
                P1gameIndex = 0
                P2gameIndex = 0
                gameP1.text = setScores[P1gameIndex].toString()
                gameP2.text = setScores[P2gameIndex].toString()
                scoreP2.text = gameScores[P2index].toString()
                P2index+=1

            }

            else{
            scoreP2.text = gameScores[P2index].toString()
            P2index+=1
            }
        }


        binding.btnFinishMatch.setOnClickListener{
            val p1name = binding.InP1Name.text.toString()
            val p2name = binding.InP2Name.text.toString()
            var p1pointsWon = P1totalpoints
            var p2pointsWon = P2totalpoints
            var pointsPlayed = totalpoints
            var p1pointsPlayed = 0
            var p2pointsPlayed = 0

            //This is inserting player 1 and 2 into database
            database = FirebaseDatabase.getInstance().getReference("Players")
            val Player1 = Player(p1name, p1pointsWon, pointsPlayed)


            database.child(p1name).get().addOnSuccessListener{

                if(it.exists()){
                    val newPointsWon = it.child("pointsWon").getValue(Int::class.java)!!
                    p1pointsWon += newPointsWon
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Int::class.java)!!
                    p1pointsPlayed = newPointsPlayed + totalpoints
                    val player = mapOf<String,Int>(
                        "pointsWon" to p1pointsWon,
                        "pointsPlayed" to p1pointsPlayed
                    )
                    database.child(p1name).updateChildren(player).addOnSuccessListener {

                        binding.InP1Name.text.clear()

                    }
                }
                else{
                    database.child(p1name).setValue(Player1).addOnSuccessListener {

                        binding.InP1Name.text.clear()

                    }
                }

            }


            val Player2 = Player(p2name, p2pointsWon, pointsPlayed)

            database.child(p2name).get().addOnSuccessListener{

                if(it.exists()){
                    val newPointsWon = it.child("pointsWon").getValue(Int::class.java)!!
                    p2pointsWon += newPointsWon
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Int::class.java)!!
                    p2pointsPlayed = newPointsPlayed + totalpoints
                    val player = mapOf<String,Int>(
                        "pointsWon" to p2pointsWon,
                        "pointsPlayed" to p2pointsPlayed
                    )
                    database.child(p2name).updateChildren(player).addOnSuccessListener {

                        binding.InP2Name.text.clear()

                    }
                }
                else{
                    database.child(p2name).setValue(Player2).addOnSuccessListener {

                        binding.InP2Name.text.clear()

                    }
                }

            }

        }

    }
}