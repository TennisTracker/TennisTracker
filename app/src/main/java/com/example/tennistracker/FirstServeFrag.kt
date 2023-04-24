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

    private val sharedViewModel: NewMatchViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_serve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPlayer1 = view.findViewById<Button>(R.id.btnPlayer1)
        val btnPlayer2 = view.findViewById<Button>(R.id.btnPlayer2)

        btnPlayer1.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_firstServeFrag_to_matchScoringFrag)
        }
        btnPlayer2.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_firstServeFrag_to_matchScoringFrag)
        }

    }
}