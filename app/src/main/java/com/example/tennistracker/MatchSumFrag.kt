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

        txtP1TotalFirstServeMissed.setText(newMatchViewModel.P1TotalFirstServeMissed.toString())
        txtP1TotalFirstServeMissed.setText(newMatchViewModel.P2TotalFirstServeMissed.toString())
        txtP1TotalSecondServeMissed.setText((newMatchViewModel.P1TotalSecondServeMissed))
        txtP2TotalSecondServeMissed.setText((newMatchViewModel.P2TotalSecondServeMissed))
        txtP1TotalReturnMissed.setText((newMatchViewModel.P1TotalReturnMissed))
        txtP2TotalReturnMissed.setText((newMatchViewModel.P2TotalReturnMissed))
        txtP1ServesTotal.setText(newMatchViewModel.P1ServesTotal)
        txtP2ServesTotal.setText(newMatchViewModel.P2ServesTotal)


        btnFinishMatch.setOnClickListener {

            val p1name = newMatchViewModel.p1Name
            val p2name = newMatchViewModel.p2Name
            var p1pointsWon = newMatchViewModel.P1totalpoints
            var p2pointsWon = newMatchViewModel.P2totalpoints
            var pointsPlayed = newMatchViewModel.totalpoints
            var p1pointsPlayed = 0
            var p2pointsPlayed = 0
            var P1TotalFirstServeMissed = newMatchViewModel.P1TotalFirstServeMissed
            var P2TotalFirstServeMissed = newMatchViewModel.P2TotalFirstServeMissed
            var P1TotalSecondServeMissed = newMatchViewModel.P1TotalSecondServeMissed
            var P2TotalSecondServeMissed = newMatchViewModel.P2TotalSecondServeMissed
            var P1TotalReturnMissed = newMatchViewModel.P1TotalReturnMissed
            var P2TotalReturnMissed = newMatchViewModel.P2TotalReturnMissed
            var P1ServesTotal = newMatchViewModel.P1ServesTotal
            var P2ServesTotal = newMatchViewModel.P2ServesTotal

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
                    database.child(p1name).updateChildren(player)
                }
                else{
                    database.child(p1name).setValue(Player1)
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