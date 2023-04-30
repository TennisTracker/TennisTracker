package com.example.tennistracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.LayoutInflater
import com.example.tennistracker.databinding.ActivityNewMatchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.activity.viewModels


//initalize array of possible game scores and set scores
val gameScores: IntArray = intArrayOf(0, 15, 30, 40)
val setScores: IntArray = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)

class NewMatch : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    //private lateinit var binding: NewMatchBinding
    private lateinit var binding: ActivityNewMatchBinding
    private lateinit var  database : DatabaseReference
    private val newMatchViewModel: NewMatchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scoreP1 = findViewById<TextView>(R.id.scoreP1)
        val scoreP2 = findViewById<TextView>(R.id.scoreP2)
        val gameP1 = findViewById<TextView>(R.id.gameP1)
        val gameP2 = findViewById<TextView>(R.id.gameP2)
        val setP1 = findViewById<TextView>(R.id.setP1)
        val setP2 = findViewById<TextView>(R.id.setP2)

        //initalize player index to 1 to combat onCreate already starting game score at 0
        newMatchViewModel.P1index = 1
        newMatchViewModel.P2index = 1



        binding.btnP1Score.setOnClickListener {
            newMatchViewModel.totalpoints += 1
            newMatchViewModel.P1totalpoints += 1

            if (setP1.text == "1" && setP2.text == "1"){
                newMatchViewModel.P1tiebreakScore +=1
                scoreP1.text = newMatchViewModel.P1tiebreakScore.toString()
                if (newMatchViewModel.P1tiebreakScore > 9 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){
                    newMatchViewModel.setIndexP1 += 1
                    setP1.text = "MATCH IS DONE"
                }
            }

            else if (newMatchViewModel.P1index == 4) {
                newMatchViewModel.P1index = 0
                newMatchViewModel.P2index = 0
                newMatchViewModel.P1gameIndex +=1
                gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
                scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
                newMatchViewModel.P2index +=1
                scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
                newMatchViewModel.P1index+=1

                if ((setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] < 5) || setScores[newMatchViewModel.P1gameIndex] == 7 && setScores[newMatchViewModel.P2gameIndex] < 6){
                    newMatchViewModel.P1gameIndex = 0
                    newMatchViewModel.P2gameIndex = 0
                    gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
                    //scoreP1.text = gameScores[P1index].toString()
                    //P1index+=1
                    newMatchViewModel.setIndexP1 += 1
                    if (newMatchViewModel.setIndexP1 == 1){
                        setP1.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP1.text = "MATCH IS DONE"
                    }
                }

            }
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                newMatchViewModel.P1tiebreakScore +=1
                scoreP1.text = newMatchViewModel.P1tiebreakScore.toString()
                if (newMatchViewModel.P1tiebreakScore > 6 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){
                    newMatchViewModel.P1gameIndex = 0
                    newMatchViewModel.P2gameIndex = 0
                    newMatchViewModel.P1index = 0
                    newMatchViewModel.P2index = 0
                    gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
                    scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
                    scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
                    newMatchViewModel.setIndexP1 += 1
                    newMatchViewModel.P1tiebreakScore = 0
                    newMatchViewModel.P2tiebreakScore = 0
                    if (newMatchViewModel.setIndexP1 == 1){
                        setP1.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP1.text = "MATCH IS DONE"
                    }
                }
            }

            else{
                scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
                newMatchViewModel.P1index+=1
            }


        }


        binding.btnP2Score.setOnClickListener {
            newMatchViewModel.totalpoints += 1
            newMatchViewModel.P2totalpoints += 1

            if (setP1.text == "1" && setP2.text == "1"){
                newMatchViewModel.P2tiebreakScore +=1
                scoreP2.text = newMatchViewModel.P2tiebreakScore.toString()
                if (newMatchViewModel.P2tiebreakScore > 9 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    newMatchViewModel.setIndexP2 += 1
                    setP2.text = "MATCH IS DONE"
                }
            }

            else if (newMatchViewModel.P2index == 4){
                newMatchViewModel.P2index = 0
                newMatchViewModel.P1index = 0
                newMatchViewModel.P2gameIndex +=1

                gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
                scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
                newMatchViewModel.P1index +=1
                scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
                newMatchViewModel.P2index+=1
                if ((setScores[newMatchViewModel.P2gameIndex] == 6 && setScores[newMatchViewModel.P1gameIndex] < 5) || setScores[newMatchViewModel.P2gameIndex] == 7 && setScores[newMatchViewModel.P1gameIndex] < 6){
                    newMatchViewModel.P1gameIndex = 0
                    newMatchViewModel.P2gameIndex = 0
                    gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
                    //scoreP2.text = gameScores[P2index].toString()
                    //P2index+=1
                    newMatchViewModel.setIndexP2 += 1
                    if (newMatchViewModel.setIndexP2 == 1){
                        setP2.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP2.text = "MATCH IS DONE"
                    }

                }

            }
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                newMatchViewModel.P2tiebreakScore +=1
                scoreP2.text = newMatchViewModel.P2tiebreakScore.toString()
                if (newMatchViewModel.P2tiebreakScore > 6 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    newMatchViewModel.P1gameIndex = 0
                    newMatchViewModel.P2gameIndex = 0
                    newMatchViewModel.P1index = 0
                    newMatchViewModel.P2index = 0
                    newMatchViewModel.P2tiebreakScore = 0
                    newMatchViewModel.P1tiebreakScore = 0
                    gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
                    scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
                    scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
                    newMatchViewModel.setIndexP2 += 1
                    if (newMatchViewModel.setIndexP2 == 1){
                        setP2.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP2.text = "MATCH IS DONE"
                    }

                }
            }


            else{
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
                newMatchViewModel.P2index+=1
            }
        }


 /*       binding.btnFinishMatch.setOnClickListener{
            val p1name = binding.InP1Name.text.toString()
            val p2name = binding.InP2Name.text.toString()
            var p1pointsWon = newMatchViewModel.P1totalpoints
            var p2pointsWon = newMatchViewModel.P2totalpoints
            var pointsPlayed = newMatchViewModel.totalpoints
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
                    p1pointsPlayed = newPointsPlayed + newMatchViewModel.totalpoints
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
                    p2pointsPlayed = newPointsPlayed + newMatchViewModel.totalpoints
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

  */

    }



}