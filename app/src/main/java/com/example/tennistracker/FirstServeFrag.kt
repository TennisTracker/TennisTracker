package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation


class FirstServeFrag : Fragment() {

    private val newMatchViewModel: NewMatchViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_serve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //initialize buttons for player 1 and player 2
        val btnPlayer1 = view.findViewById<Button>(R.id.btnPlayer1)
        val btnPlayer2 = view.findViewById<Button>(R.id.btnPlayer2)

        //set the text of p1 and p2 to the names given on previous page
        btnPlayer1.setText(newMatchViewModel.p1Name)
        btnPlayer2.setText(newMatchViewModel.p2Name)

        btnPlayer1.setOnClickListener{
            //set firstserver to p1 and pull up match scoring frag
            newMatchViewModel.FirstServer = 1
            Navigation.findNavController(view).navigate(R.id.action_firstServeFrag_to_matchScoringFrag)
        }
        btnPlayer2.setOnClickListener{
            //set firstserver to p2 and pull up match scoring frag
            newMatchViewModel.FirstServer = 2
            Navigation.findNavController(view).navigate(R.id.action_firstServeFrag_to_matchScoringFrag)
        }

    }
}