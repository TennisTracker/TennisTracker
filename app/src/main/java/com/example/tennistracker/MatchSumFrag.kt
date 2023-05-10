package com.example.tennistracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.tennistracker.NewMatchViewModel
import androidx.fragment.app.activityViewModels
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.roundToInt
import kotlin.math.round
import java.text.SimpleDateFormat
import java.util.*


class MatchSumFrag : Fragment() {

    private val newMatchViewModel: NewMatchViewModel by activityViewModels()
    private lateinit var  database : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val txtP1Name = view.findViewById<TextView>(R.id.txtP1Name)
        val txtP2Name = view.findViewById<TextView>(R.id.txtP2Name)
        val txtP1PointsWon = view.findViewById<TextView>(R.id.txtP1PointsWon)
        val txtP2PointsWon = view.findViewById<TextView>(R.id.txtP2PointsWon)
        val txtP1PointsPlayed = view.findViewById<TextView>(R.id.txtP1PointsPlayed)
        val txtP2PointsPlayed = view.findViewById<TextView>(R.id.txtP2PointsPlayed)

        val txtP1TotalFirstServeMissed = view.findViewById<TextView>(R.id.txtP1TotalFirstServeMissed)
        val txtP2TotalFirstServeMissed = view.findViewById<TextView>(R.id.txtP2TotalFirstServeMissed)
        val txtP1TotalSecondServeMissed = view.findViewById<TextView>(R.id.txtP1TotalSecondServeMissed)
        val txtP2TotalSecondServeMissed = view.findViewById<TextView>(R.id.txtP2TotalSecondServeMissed)
        val txtP1TotalReturnMissed = view.findViewById<TextView>(R.id.txtP1TotalReturnMissed)
        val txtP2TotalReturnMissed = view.findViewById<TextView>(R.id.txtP2TotalReturnMissed)
        val txtP1ServesTotal = view.findViewById<TextView>(R.id.txtP1ServesTotal)
        val txtP2ServesTotal = view.findViewById<TextView>(R.id.txtP2ServesTotal)
        val btnFinishMatch = view.findViewById<Button>(R.id.btnFinishMatch)




        txtP1Name.setText(newMatchViewModel.p1Name)
        txtP2Name.setText(newMatchViewModel.p2Name)
        txtP1PointsWon.setText(newMatchViewModel.P1totalpoints.toString())
        txtP2PointsWon.setText(newMatchViewModel.P2totalpoints.toString())
        txtP1PointsPlayed.setText((newMatchViewModel.P1totalpoints * 100.0 / newMatchViewModel.totalpoints).roundToInt().toString())
        txtP2PointsPlayed.setText((newMatchViewModel.P2totalpoints * 100.0 / newMatchViewModel.totalpoints).roundToInt().toString())

        //first serve percentage
        txtP1TotalFirstServeMissed.setText((((newMatchViewModel.P1ServesTotal - newMatchViewModel.P1TotalFirstServeMissed) * 100.0)/newMatchViewModel.P1ServesTotal).roundToInt().toString())
        txtP2TotalFirstServeMissed.setText((((newMatchViewModel.P2ServesTotal - newMatchViewModel.P2TotalFirstServeMissed) * 100.0)/newMatchViewModel.P2ServesTotal).roundToInt().toString())

        //second serve percentage
        txtP1TotalSecondServeMissed.setText(((newMatchViewModel.P1TotalFirstServeMissed - newMatchViewModel.P1TotalSecondServeMissed) * 100.0 / (newMatchViewModel.P1TotalFirstServeMissed) ).toString())
        txtP2TotalSecondServeMissed.setText(((newMatchViewModel.P2TotalFirstServeMissed - newMatchViewModel.P2TotalSecondServeMissed) * 100.0 / (newMatchViewModel.P2TotalFirstServeMissed) ).toString())

        //return percentage
        txtP1TotalReturnMissed.setText(((newMatchViewModel.P1ServesTotal - newMatchViewModel.P1TotalFirstServeMissed - newMatchViewModel.P1TotalSecondServeMissed - newMatchViewModel.P1TotalReturnMissed)* 100.0 / (newMatchViewModel.P1ServesTotal - newMatchViewModel.P1TotalFirstServeMissed - newMatchViewModel.P1TotalSecondServeMissed)).roundToInt().toString())
        txtP2TotalReturnMissed.setText(((newMatchViewModel.P2ServesTotal - newMatchViewModel.P2TotalFirstServeMissed - newMatchViewModel.P2TotalSecondServeMissed - newMatchViewModel.P2TotalReturnMissed) * 100.0/ (newMatchViewModel.P2ServesTotal - newMatchViewModel.P2TotalFirstServeMissed - newMatchViewModel.P2TotalSecondServeMissed)).roundToInt().toString())

        //total serves hit
        txtP1ServesTotal.setText(newMatchViewModel.P1ServesTotal.toString())
        txtP2ServesTotal.setText(newMatchViewModel.P2ServesTotal.toString())

        btnFinishMatch.setOnClickListener {




            val p1name = newMatchViewModel.p1Name
            val p2name = newMatchViewModel.p2Name
            var p1pointsWon = newMatchViewModel.P1totalpoints
            var p2pointsWon = newMatchViewModel.P2totalpoints
            var pointsPlayed = newMatchViewModel.totalpoints
            var p1pointsPlayed = 0.0
            var p2pointsPlayed = 0.0
            var P1TotalFirstServeMissed = newMatchViewModel.P1TotalFirstServeMissed
            var P2TotalFirstServeMissed = newMatchViewModel.P2TotalFirstServeMissed
            var P1TotalSecondServeMissed = newMatchViewModel.P1TotalSecondServeMissed
            var P2TotalSecondServeMissed = newMatchViewModel.P2TotalSecondServeMissed
            var P1TotalReturnMissed = newMatchViewModel.P1TotalReturnMissed
            var P2TotalReturnMissed = newMatchViewModel.P2TotalReturnMissed
            var P1ServesTotal = newMatchViewModel.P1ServesTotal
            var P2ServesTotal = newMatchViewModel.P2ServesTotal

            //This will get the current date and time
            val sdf = SimpleDateFormat("MM-dd-yyyy ''HH:mm:ss z")
            val currentDateAndTime = sdf.format(Date())


            //Get the number of matches in the database
            database = FirebaseDatabase.getInstance().getReference("Players")
            var numberMatches = 0
            database.child("numMatches").get().addOnSuccessListener {
                if(it.exists()){
                    numberMatches = it.child("matches").getValue(Int::class.java)!!
                    var newNumberMatches = numberMatches + 1

                    val constant = mapOf<String,Int>(
                        "matches" to newNumberMatches
                    )

                    database.child("numMatches").updateChildren(constant)
                }
                else{
                    val constant = DatabaseConstants(0)
                    database.child("numMatches").setValue(constant)
                }
            }


            //Send match information to the database
            database = FirebaseDatabase.getInstance().getReference("Players")

            //Get the match score
            val match_Score1 = newMatchViewModel.Set1P1 + " - " + newMatchViewModel.Set1P2
            val match_Score2 = newMatchViewModel.Set2P1 + " - " + newMatchViewModel.Set2P2
            val match_Score3 = newMatchViewModel.Set3P1 + " - " + newMatchViewModel.Set3P2
            var final_Score = "Match 1: " + match_Score1 + "\nMatch 2: " + match_Score2

            //If the third set is played then add it to the final score
            if((newMatchViewModel.Set1P1.toInt() > newMatchViewModel.Set1P2.toInt()) && (newMatchViewModel.Set2P1.toInt() < newMatchViewModel.Set2P2.toInt())){
                final_Score = final_Score + "\nMatch 3: " + match_Score3
            }
            if((newMatchViewModel.Set1P1.toInt() < newMatchViewModel.Set1P2.toInt()) && (newMatchViewModel.Set2P1.toInt() > newMatchViewModel.Set2P2.toInt())){
                final_Score = final_Score + "\nMatch 3: " + match_Score3
            }

            val match = Match(p1name, p1pointsWon, P1TotalFirstServeMissed, P1TotalSecondServeMissed, P1TotalReturnMissed,
            P1ServesTotal, p2name, p2pointsWon, P2TotalFirstServeMissed, P2TotalSecondServeMissed, P2TotalReturnMissed,
            P2ServesTotal, currentDateAndTime, final_Score)

            database.child(numberMatches.toString()).get().addOnSuccessListener {
                database.child(numberMatches.toString()).setValue(match)
            }


            //Start sending player info to database
            database = FirebaseDatabase.getInstance().getReference("Players")
            val Player1 = Player(p1name, p1pointsWon, pointsPlayed, P1TotalFirstServeMissed,
                P1TotalSecondServeMissed, P1TotalReturnMissed, P1ServesTotal)

            database.child(p1name).get().addOnSuccessListener{

                if(it.exists()){
                    //This lines get previous data and add new totals
                    val newPointsWon = it.child("pointsWon").getValue(Double::class.java)!!
                    p1pointsWon += newPointsWon
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Double::class.java)!!
                    p1pointsPlayed = newPointsPlayed + newMatchViewModel.totalpoints
                    val newFirstServeMissed = it.child("totalFirstServeMissed").getValue(Double::class.java)!!
                    P1TotalFirstServeMissed += newFirstServeMissed
                    val newSecondServeMissed = it.child("totalSecondServeMissed").getValue(Double::class.java)!!
                    P1TotalSecondServeMissed += newSecondServeMissed
                    val newTotalServeMissed = it.child("totalReturnMissed").getValue(Double::class.java)!!
                    P1TotalReturnMissed += newTotalServeMissed
                    val newServesTotal = it.child("servesTotal").getValue(Double::class.java)!!
                    P1ServesTotal += newServesTotal

                    //This make hashmap of data to update the player info
                    val player = mapOf<String,Double>(
                        "pointsWon" to p1pointsWon,
                        "pointsPlayed" to p1pointsPlayed,
                        "totalFirstServeMissed" to P1TotalFirstServeMissed,
                        "totalSecondServeMissed" to P1TotalSecondServeMissed,
                        "totalReturnMissed" to P1TotalReturnMissed,
                        "servesTotal" to P1ServesTotal
                    )
                    database.child(p1name).updateChildren(player)
                }
                else{
                    database.child(p1name).setValue(Player1)
                }

            }


            val Player2 = Player(p2name, p2pointsWon, pointsPlayed, P2TotalFirstServeMissed,
                P2TotalSecondServeMissed, P2TotalReturnMissed, P2ServesTotal)
            database.child(p2name).get().addOnSuccessListener{

                if(it.exists()){
                    //This lines get previous data and add new totals
                    val newPointsWon = it.child("pointsWon").getValue(Double::class.java)!!
                    p2pointsWon += newPointsWon
                    val newPointsPlayed = it.child("pointsPlayed").getValue(Double::class.java)!!
                    p2pointsPlayed = newPointsPlayed + newMatchViewModel.totalpoints
                    val newFirstServeMissed = it.child("totalFirstServeMissed").getValue(Double::class.java)!!
                    P2TotalFirstServeMissed += newFirstServeMissed
                    val newSecondServeMissed = it.child("totalSecondServeMissed").getValue(Double::class.java)!!
                    P2TotalSecondServeMissed += newSecondServeMissed
                    val newTotalServeMissed = it.child("totalReturnMissed").getValue(Double::class.java)!!
                    P2TotalReturnMissed += newTotalServeMissed
                    val newServesTotal = it.child("servesTotal").getValue(Double::class.java)!!
                    P2ServesTotal += newServesTotal

                    //This make hashmap of data to update the player info
                    val player = mapOf<String,Double>(
                        "pointsWon" to p2pointsWon,
                        "pointsPlayed" to p2pointsPlayed,
                        "totalFirstServeMissed" to P2TotalFirstServeMissed,
                        "totalSecondServeMissed" to P2TotalSecondServeMissed,
                        "totalReturnMissed" to P2TotalReturnMissed,
                        "servesTotal" to P2ServesTotal
                    )
                    database.child(p2name).updateChildren(player)
                }
                else{
                    database.child(p2name).setValue(Player2)
                }

            }


            val intent = Intent(getActivity(), MainActivity::class.java)
            getActivity()?.startActivity(intent)
        }




    }

}