package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.tennistracker.NewMatchViewModel
import androidx.fragment.app.activityViewModels


class MatchScoringFrag : Fragment() {

    private val newMatchViewModel: NewMatchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_scoring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var totalpoints = 0
        var P1totalpoints = 0
        var P2totalpoints = 0
        var P1index = 1
        var P2index = 1
        var P1gameIndex = 0
        var P2gameIndex = 0
        var P1tiebreakScore = 0
        var P2tiebreakScore = 0
        var setIndexP1 = 0
        var setIndexP2 = 0


        val btnP1Score = view.findViewById<Button>(R.id.btnP1Score)
        val btnP2Score = view.findViewById<Button>(R.id.btnP2Score)
        val scoreP1 = view.findViewById<TextView>(R.id.scoreP1)
        val scoreP2 = view.findViewById<TextView>(R.id.scoreP2)
        val setP1 = view.findViewById<TextView>(R.id.setP1)
        val setP2 = view.findViewById<TextView>(R.id.setP2)
        val gameP1 = view.findViewById<TextView>(R.id.gameP1)
        val gameP2 = view.findViewById<TextView>(R.id.gameP2)


        btnP1Score.setOnClickListener{
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


        btnP2Score.setOnClickListener {
            totalpoints += 1
            P2totalpoints += 1

            if (setP1.text == "1" && setP2.text == "1"){
                P2tiebreakScore +=1
                scoreP2.text = P2tiebreakScore.toString()
                if (P2tiebreakScore > 9 && (P2tiebreakScore - P1tiebreakScore) > 1){
                    setIndexP2 += 1
                    setP2.text = "MATCH IS DONE"
                    Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                }
            }

            else if (P2index == 4){
                P2index = 0
                P1index = 0
                P2gameIndex +=1

                gameP2.text = setScores[P2gameIndex].toString()
                scoreP1.text = gameScores[P1index].toString()
                P1index +=1
                scoreP2.text = gameScores[P2index].toString()
                P2index+=1
                if ((setScores[P2gameIndex] == 6 && setScores[P1gameIndex] < 5) || setScores[P2gameIndex] == 7 && setScores[P1gameIndex] < 6){
                    P1gameIndex = 0
                    P2gameIndex = 0
                    gameP1.text = setScores[P1gameIndex].toString()
                    gameP2.text = setScores[P2gameIndex].toString()
                    //scoreP2.text = gameScores[P2index].toString()
                    //P2index+=1
                    setIndexP2 += 1
                    if (setIndexP2 == 1){
                        setP2.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP2.text = "MATCH IS DONE"
                        Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                    }

                }

            }
            else if (setScores[P1gameIndex] == 6 && setScores[P2gameIndex] == 6){
                P2tiebreakScore +=1
                scoreP2.text = P2tiebreakScore.toString()
                if (P2tiebreakScore > 6 && (P2tiebreakScore - P1tiebreakScore) > 1){
                    P1gameIndex = 0
                    P2gameIndex = 0
                    P1index = 0
                    P2index = 0
                    P2tiebreakScore = 0
                    P1tiebreakScore = 0
                    gameP1.text = setScores[P1gameIndex].toString()
                    gameP2.text = setScores[P2gameIndex].toString()
                    scoreP1.text = gameScores[P1index].toString()
                    scoreP2.text = gameScores[P2index].toString()
                    setIndexP2 += 1
                    if (setIndexP2 == 1){
                        setP2.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP2.text = "MATCH IS DONE"
                        Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)

                    }

                }
            }


            else{
                scoreP2.text = gameScores[P2index].toString()
                P2index+=1
            }
        }


    }
}