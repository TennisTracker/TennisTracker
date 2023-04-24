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

    private val sharedViewModel: NewMatchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_scoring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        sharedViewModel.P1index = 1
        sharedViewModel.P2index = 1



        val btnP1Score = view.findViewById<Button>(R.id.btnP1Score)
        val btnP2Score = view.findViewById<Button>(R.id.btnP2Score)
        val scoreP1 = view.findViewById<TextView>(R.id.scoreP1)
        val scoreP2 = view.findViewById<TextView>(R.id.scoreP2)
        val setP1 = view.findViewById<TextView>(R.id.setP1)
        val setP2 = view.findViewById<TextView>(R.id.setP2)
        val gameP1 = view.findViewById<TextView>(R.id.gameP1)
        val gameP2 = view.findViewById<TextView>(R.id.gameP2)


        btnP1Score.setOnClickListener{
            sharedViewModel.totalpoints += 1
            sharedViewModel.P1totalpoints += 1

            if (setP1.text == "1" && setP2.text == "1"){
                sharedViewModel.P1tiebreakScore +=1
                scoreP1.text = sharedViewModel.P1tiebreakScore.toString()
                if (sharedViewModel.P1tiebreakScore > 9 && (sharedViewModel.P1tiebreakScore - sharedViewModel.P2tiebreakScore) > 1){
                    sharedViewModel.setIndexP1 += 1
                    setP1.text = "MATCH IS DONE"
                    Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                }
            }

            else if (sharedViewModel.P1index == 4) {
                sharedViewModel.P1index = 0
                sharedViewModel.P2index = 0
                sharedViewModel.P1gameIndex +=1
                gameP1.text = setScores[sharedViewModel.P1gameIndex].toString()
                scoreP2.text = gameScores[sharedViewModel.P2index].toString()
                sharedViewModel.P2index +=1
                scoreP1.text = gameScores[sharedViewModel.P1index].toString()
                sharedViewModel.P1index+=1

                if ((setScores[sharedViewModel.P1gameIndex] == 6 && setScores[sharedViewModel.P2gameIndex] < 5) || setScores[sharedViewModel.P1gameIndex] == 7 && setScores[sharedViewModel.P2gameIndex] < 6){
                    sharedViewModel.P1gameIndex = 0
                    sharedViewModel.P2gameIndex = 0
                    gameP1.text = setScores[sharedViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[sharedViewModel.P2gameIndex].toString()
                    //scoreP1.text = gameScores[P1index].toString()
                    //P1index+=1
                    sharedViewModel.setIndexP1 += 1
                    if (sharedViewModel.setIndexP1 == 1){
                        setP1.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP1.text = "MATCH IS DONE"
                        Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                    }
                }

            }
            else if (setScores[sharedViewModel.P1gameIndex] == 6 && setScores[sharedViewModel.P2gameIndex] == 6){
                sharedViewModel.P1tiebreakScore +=1
                scoreP1.text = sharedViewModel.P1tiebreakScore.toString()
                if (sharedViewModel.P1tiebreakScore > 6 && (sharedViewModel.P1tiebreakScore - sharedViewModel.P2tiebreakScore) > 1){
                    sharedViewModel.P1gameIndex = 0
                    sharedViewModel.P2gameIndex = 0
                    sharedViewModel.P1index = 0
                    sharedViewModel.P2index = 0
                    gameP1.text = setScores[sharedViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[sharedViewModel.P2gameIndex].toString()
                    scoreP1.text = gameScores[sharedViewModel.P1index].toString()
                    scoreP2.text = gameScores[sharedViewModel.P2index].toString()
                    sharedViewModel.setIndexP1 += 1
                    sharedViewModel.P1tiebreakScore = 0
                    sharedViewModel.P2tiebreakScore = 0
                    if (sharedViewModel.setIndexP1 == 1){
                        setP1.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP1.text = "MATCH IS DONE"
                        Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                    }
                }
            }

            else{
                scoreP1.text = gameScores[sharedViewModel.P1index].toString()
                sharedViewModel.P1index+=1
            }


        }


        btnP2Score.setOnClickListener {
            sharedViewModel.totalpoints += 1
            sharedViewModel.P2totalpoints += 1

            if (setP1.text == "1" && setP2.text == "1"){
                sharedViewModel.P2tiebreakScore +=1
                scoreP2.text = sharedViewModel.P2tiebreakScore.toString()
                if (sharedViewModel.P2tiebreakScore > 9 && (sharedViewModel.P2tiebreakScore - sharedViewModel.P1tiebreakScore) > 1){
                    sharedViewModel.setIndexP2 += 1
                    setP2.text = "MATCH IS DONE"
                    Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                }
            }

            else if (sharedViewModel.P2index == 4){
                sharedViewModel.P2index = 0
                sharedViewModel.P1index = 0
                sharedViewModel.P2gameIndex +=1

                gameP2.text = setScores[sharedViewModel.P2gameIndex].toString()
                scoreP1.text = gameScores[sharedViewModel.P1index].toString()
                sharedViewModel.P1index +=1
                scoreP2.text = gameScores[sharedViewModel.P2index].toString()
                sharedViewModel.P2index+=1
                if ((setScores[sharedViewModel.P2gameIndex] == 6 && setScores[sharedViewModel.P1gameIndex] < 5) || setScores[sharedViewModel.P2gameIndex] == 7 && setScores[sharedViewModel.P1gameIndex] < 6){
                    sharedViewModel.P1gameIndex = 0
                    sharedViewModel.P2gameIndex = 0
                    gameP1.text = setScores[sharedViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[sharedViewModel.P2gameIndex].toString()
                    //scoreP2.text = gameScores[P2index].toString()
                    //P2index+=1
                    sharedViewModel.setIndexP2 += 1
                    if (sharedViewModel.setIndexP2 == 1){
                        setP2.text = "1"
                    }
                    else{
                        //MATCH DONE FUNCTION
                        setP2.text = "MATCH IS DONE"
                        Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                    }

                }

            }
            else if (setScores[sharedViewModel.P1gameIndex] == 6 && setScores[sharedViewModel.P2gameIndex] == 6){
                sharedViewModel.P2tiebreakScore +=1
                scoreP2.text = sharedViewModel.P2tiebreakScore.toString()
                if (sharedViewModel.P2tiebreakScore > 6 && (sharedViewModel.P2tiebreakScore - sharedViewModel.P1tiebreakScore) > 1){
                    sharedViewModel.P1gameIndex = 0
                    sharedViewModel.P2gameIndex = 0
                    sharedViewModel.P1index = 0
                    sharedViewModel.P2index = 0
                    sharedViewModel.P2tiebreakScore = 0
                    sharedViewModel.P1tiebreakScore = 0
                    gameP1.text = setScores[sharedViewModel.P1gameIndex].toString()
                    gameP2.text = setScores[sharedViewModel.P2gameIndex].toString()
                    scoreP1.text = gameScores[sharedViewModel.P1index].toString()
                    scoreP2.text = gameScores[sharedViewModel.P2index].toString()
                    sharedViewModel.setIndexP2 += 1
                    if (sharedViewModel.setIndexP2 == 1){
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
                scoreP2.text = gameScores[sharedViewModel.P2index].toString()
                sharedViewModel.P2index+=1
            }
        }


    }
}