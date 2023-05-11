package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class prevMatchStatsFragment : Fragment() {

    private val PrevMatchViewModel: PrevMatchViewModel by activityViewModels()
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_match_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MatchScore = view.findViewById<TextView>(R.id.textView3)

        val txtP1Name = view.findViewById<TextView>(R.id.txtP1Name)
        val txtP2Name = view.findViewById<TextView>(R.id.txtP2Name)

        val txtP1PointsWon = view.findViewById<TextView>(R.id.txtP1PointsWon)
        val txtP2PointsWon = view.findViewById<TextView>(R.id.txtP2PointsWon)

        val txtP1TotalFirstServeMissed = view.findViewById<TextView>(R.id.txtP1TotalFirstServeMissed)
        val txtP2TotalFirstServeMissed = view.findViewById<TextView>(R.id.txtP2TotalFirstServeMissed)

        val txtP1TotalSecondServeMissed = view.findViewById<TextView>(R.id.txtP1TotalSecondServeMissed)
        val txtP2TotalSecondServeMissed = view.findViewById<TextView>(R.id.txtP2TotalSecondServeMissed)

        val txtP1TotalReturnMissed = view.findViewById<TextView>(R.id.txtP1TotalReturnMissed)
        val txtP2TotalReturnMissed = view.findViewById<TextView>(R.id.txtP2TotalReturnMissed)

        val txtP1ServesTotal = view.findViewById<TextView>(R.id.txtP1ServesTotal)
        val txtP2ServesTotal = view.findViewById<TextView>(R.id.txtP2ServesTotal)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child((PrevMatchViewModel.prevMatchIndex).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)

                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)

                val p1PointsWon = it.child("p1pointsWon").getValue(Double::class.java)
                val p2PointsWon = it.child("p2pointsWon").getValue(Double::class.java)

                val p1firstServe = it.child("p1totalFirstServeMissed").getValue(Double::class.java)
                val p2firstServe = it.child("p2totalFirstServeMissed").getValue(Double::class.java)

                val p1secondServe = it.child("p1totalSecondServeMissed").getValue(Double::class.java)
                val p2secondServe = it.child("p2totalSecondServeMissed").getValue(Double::class.java)

                val p1ReturnMiss = it.child("p1totalReturnMissed").getValue(Double::class.java)
                val p2ReturnMiss = it.child("p2totalReturnMissed").getValue(Double::class.java)

                val p1servesTotal = it.child("p1servesTotal").getValue(Double::class.java)
                val p2servesTotal = it.child("p2servesTotal").getValue(Double::class.java)

                txtP1Name.text = p1name
                txtP2Name.text = p2name

                txtP1PointsWon.text = p1PointsWon.toString()
                txtP2PointsWon.text = p2PointsWon.toString()

                txtP1TotalFirstServeMissed.text = p1firstServe.toString()
                txtP2TotalFirstServeMissed.text = p2firstServe.toString()

                txtP1TotalSecondServeMissed.text = p1secondServe.toString()
                txtP2TotalSecondServeMissed.text = p2secondServe.toString()

                txtP1TotalReturnMissed.text = p1ReturnMiss.toString()
                txtP2TotalReturnMissed.text = p2ReturnMiss.toString()

                txtP1ServesTotal.text = p1servesTotal.toString()
                txtP2ServesTotal.text = p2servesTotal.toString()

                MatchScore.text = score

            }


        }

        val btnReturn = view.findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_prevMatchStatsFragment_to_pageListFragment)
        }

    }
}