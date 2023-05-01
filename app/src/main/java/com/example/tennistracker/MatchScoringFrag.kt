package com.example.tennistracker

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation


class MatchScoringFrag : Fragment() {

    private val newMatchViewModel: NewMatchViewModel by activityViewModels()

    lateinit var btnP1Score: Button
    lateinit var btnP2Score: Button
    lateinit var btnFirstServeMiss: Button
    lateinit var btnSecondServeMiss: Button
    lateinit var btnReturnMiss: Button
    lateinit var scoreP1 : TextView
    lateinit var scoreP2 : TextView
    lateinit var setP1 : TextView
    lateinit var setP2 : TextView
    lateinit var gameP1 : TextView
    lateinit var gameP2 : TextView




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_scoring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val P1NameText = view.findViewById<TextView>(R.id.P1name)
        val P2NameText = view.findViewById<TextView>(R.id.P2name)
        P1NameText.setText(newMatchViewModel.p1Name)
        P2NameText.setText(newMatchViewModel.p2Name)


        btnP1Score = view.findViewById<Button>(R.id.btnP1Score)
        btnP2Score = view.findViewById<Button>(R.id.btnP2Score)
        btnFirstServeMiss = view.findViewById<Button>(R.id.FirstServeMissed)
        btnSecondServeMiss = view.findViewById<Button>(R.id.SecondServeMissed)
        btnReturnMiss = view.findViewById<Button>(R.id.ReturnMissed)
        scoreP1 = view.findViewById<TextView>(R.id.scoreP1)
        scoreP2 = view.findViewById<TextView>(R.id.scoreP2)
        setP1 = view.findViewById<TextView>(R.id.setP1)
        setP2 = view.findViewById<TextView>(R.id.setP2)
        gameP1 = view.findViewById<TextView>(R.id.gameP1)
        gameP2 = view.findViewById<TextView>(R.id.gameP2)




        btnP1Score.setOnClickListener{
            view.performHapticFeedback(100)
            ServesHit()
            if (setP1.text == "1" && setP2.text == "1"){
                tiebreak(1)
                if (newMatchViewModel.P1tiebreakScore > 9 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){
                    newMatchViewModel.setIndexP1 += 1
                    Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                }
            }
            else if (newMatchViewModel.P1index == 4) {
                gameScored(1)
                if ((setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] < 5) || setScores[newMatchViewModel.P1gameIndex] == 7 && setScores[newMatchViewModel.P2gameIndex] < 6){
                    finishSet(1, view)
                }
            }
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                tiebreak(1)
                if (newMatchViewModel.P1tiebreakScore > 6 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){
                    finishSet(1, view)
                }
            }
            else{
                pointScored(1)
            }


        }


        btnP2Score.setOnClickListener {
            view.performHapticFeedback(100)

            ServesHit()
            if (setP1.text == "1" && setP2.text == "1"){
                tiebreak(2)
                if (newMatchViewModel.P2tiebreakScore > 9 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    newMatchViewModel.setIndexP2 += 1
                    Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
                }
            }
            else if (newMatchViewModel.P2index == 4) {
                gameScored(2)

                if ((setScores[newMatchViewModel.P2gameIndex] == 6 && setScores[newMatchViewModel.P1gameIndex] < 5) || setScores[newMatchViewModel.P2gameIndex] == 7 && setScores[newMatchViewModel.P1gameIndex] < 6){
                    finishSet(2, view)
                }

            }
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                tiebreak(2)
                if (newMatchViewModel.P2tiebreakScore > 6 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    finishSet(2, view)
                }
            }
            else{
                pointScored(2)
            }
        }


        btnFirstServeMiss.setOnClickListener{
            if (newMatchViewModel.FirstServer == 1){
                newMatchViewModel.P1TotalFirstServeMissed += 1
            }
            else if (newMatchViewModel.FirstServer == 2){
                newMatchViewModel.P2TotalFirstServeMissed += 1
            }
            ServesHit()
        }


        btnSecondServeMiss.setOnClickListener {
            if (newMatchViewModel.FirstServer == 2) {
                newMatchViewModel.P2TotalSecondServeMissed +=1
                btnP1Score.performClick()
            }
            else if (newMatchViewModel.FirstServer == 1) {
                newMatchViewModel.P1TotalSecondServeMissed +=1
                btnP2Score.performClick()
            }
        }
        btnReturnMiss.setOnClickListener {
            if (newMatchViewModel.FirstServer == 1) {
                newMatchViewModel.P2TotalReturnMissed +=1
                btnP1Score.performClick()
            }

            else if (newMatchViewModel.FirstServer == 2) {
                newMatchViewModel.P1TotalReturnMissed +=1
                btnP2Score.performClick()
            }
        }

    }

    public fun pointScored(i:Int){
        if (i == 1){
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            newMatchViewModel.P1index += 1
            newMatchViewModel.P1totalpoints +=1
        }
        else if (i==2){
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            newMatchViewModel.P2index += 1
            newMatchViewModel.P2totalpoints +=1
        }
        newMatchViewModel.totalpoints +=1
    }


    public fun gameScored(i:Int){
        if (i==1){
            pointsReset()
            newMatchViewModel.P1gameIndex +=1
            gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            newMatchViewModel.P2index +=1
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            newMatchViewModel.P1index+=1
            newMatchViewModel.P1totalpoints +=1

        }
        else if (i==2){
            pointsReset()
            newMatchViewModel.P2gameIndex +=1
            gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            newMatchViewModel.P1index +=1
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            newMatchViewModel.P2index+=1
            newMatchViewModel.P2totalpoints +=1
        }
        newMatchViewModel.totalpoints +=1
        changeServer()
    }

    public fun pointsReset (){
        newMatchViewModel.P2index = 0
        newMatchViewModel.P1index = 0
    }
    public fun gamesReset(){
        newMatchViewModel.P1gameIndex = 0
        newMatchViewModel.P2gameIndex = 0
    }

    public fun tiebreak(i:Int){
        if (i == 1){
            newMatchViewModel.P1tiebreakScore +=1
            scoreP1.text = newMatchViewModel.P1tiebreakScore.toString()
            newMatchViewModel.P1totalpoints += 1
        }
        else if (i == 2){
            newMatchViewModel.P2tiebreakScore +=1
            scoreP2.text = newMatchViewModel.P2tiebreakScore.toString()
            newMatchViewModel.P2totalpoints += 1
        }
        newMatchViewModel.totalpoints += 1
        if ((newMatchViewModel.P1tiebreakScore == 1 && newMatchViewModel.P2tiebreakScore == 0) || (newMatchViewModel.P2tiebreakScore == 1 && newMatchViewModel.P1tiebreakScore == 0)) {
            changeServer()
            }
        else if ((((newMatchViewModel.P1tiebreakScore + newMatchViewModel.P2tiebreakScore) - 1) % 2) == 0){
            changeServer()
        }

    }

    public fun finishSet(i:Int, view:View){
        pointsReset()
        gamesReset()
        gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
        gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
        if (i == 1){
            newMatchViewModel.setIndexP1 += 1
            if (newMatchViewModel.setIndexP1 == 1){
                setP1.text = "1"
            }
            else{
                //MATCH DONE FUNCTION
                Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
            }
        }
        else if (i == 2){
            newMatchViewModel.setIndexP2 += 1
            if (newMatchViewModel.setIndexP2 == 1){
                setP2.text = "1"
            }
            else{
                //MATCH DONE FUNCTION
                setP2.text = "MATCH IS DONE"
                Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
            }
        }
        newMatchViewModel.P1tiebreakScore = 0
        newMatchViewModel.P2tiebreakScore = 0
        scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
        scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
        newMatchViewModel.P1index = 1
        newMatchViewModel.P2index = 1
    }
    public fun changeServer(){
        if (newMatchViewModel.FirstServer == 1){
            newMatchViewModel.FirstServer = 2
        }
        else{
            newMatchViewModel.FirstServer = 1
        }
    }
    public fun ServesHit(){
        if (newMatchViewModel.FirstServer ==1){
            newMatchViewModel.P1ServesTotal += 1
        }
        else if (newMatchViewModel.FirstServer ==2){
            newMatchViewModel.P2ServesTotal +=1
        }
    }

    private fun shakeItBaby(context: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(150)
        }
    }

}
