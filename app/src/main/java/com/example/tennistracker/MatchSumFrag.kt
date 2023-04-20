package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tennistracker.NewMatchViewModel
import androidx.fragment.app.activityViewModels


class MatchSumFrag : Fragment() {

    private val sharedViewModel: NewMatchViewModel by activityViewModels()

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

        val p1name = sharedViewModel.p1Name
        val p2name = sharedViewModel.p2Name

        txtP1Name.setText(p1name)
        txtP2Name.setText(p2name)


    }

}