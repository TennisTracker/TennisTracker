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
        val btnFinishMatch = view.findViewById<Button>(R.id.btnFinishMatch)


        txtP1Name.setText(newMatchViewModel.p1Name)
        txtP2Name.setText(newMatchViewModel.p2Name)
        txtP1PointsWon.setText(newMatchViewModel.P1totalpoints.toString())
        txtP2PointsWon.setText(newMatchViewModel.P2totalpoints.toString())
        txtP1PointsPlayed.setText((newMatchViewModel.P1totalpoints * 100.0 / newMatchViewModel.totalpoints).roundToInt().toString())
        txtP2PointsPlayed.setText((newMatchViewModel.P1totalpoints * 100.0 / newMatchViewModel.totalpoints).roundToInt().toString())

        btnFinishMatch.setOnClickListener {

            val p1name = newMatchViewModel.p1Name
            val p2name = newMatchViewModel.p2Name
            var p1pointsWon = newMatchViewModel.P1totalpoints
            var p2pointsWon = newMatchViewModel.P2totalpoints
            var pointsPlayed = newMatchViewModel.totalpoints
            var p1pointsPlayed = 0
            var p2pointsPlayed = 0

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