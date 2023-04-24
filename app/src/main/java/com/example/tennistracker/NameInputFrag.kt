package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation


class NameInputFrag : Fragment() {

    private val newMatchViewModel: NewMatchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val p1NameInput = view.findViewById<EditText>(R.id.p1NameInput)
        val p2NameInput = view.findViewById<EditText>(R.id.p2NameInput)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener{
            val P1Name = p1NameInput.text.toString()
            val P2Name = p2NameInput.text.toString()
            newMatchViewModel.p1Name = P1Name
            newMatchViewModel.p2Name = P2Name
            Navigation.findNavController(view).navigate(R.id.action_nameInputFrag_to_firstServeFrag)
        }

    }
}