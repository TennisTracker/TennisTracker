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


        //create buttons and textviews for match scoring
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


        //Set the Score, game, and set onCreate
        scoreP1.text = gameScores[newMatchViewModel.P1index - 1].toString()
        scoreP2.text = gameScores[newMatchViewModel.P2index - 1].toString()
        gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
        gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
        setP1.text = newMatchViewModel.setIndexP1.toString()
        setP2.text = newMatchViewModel.setIndexP2.toString()


        btnP1Score.setOnClickListener{
            //when player one button is pressed, note that a serve must be hit
            ServesHit()

            //if both players won a set, start condition for third set tiebreak
            if (setP1.text == "1" && setP2.text == "1"){
                tiebreak(1)

                //if p1 won the tiebreak, end the tiebreak and finish  match
                if (newMatchViewModel.P1tiebreakScore > 9 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){
                    newMatchViewModel.setIndexP1 += 1
                    finishSet(1, view)
                }
            }
            //if the game has been scored, reset game scoring and add one game scored to P1
            else if (newMatchViewModel.P1index == 4) {
                gameScored(1)
                //if total game scored for p1 is enough for set, reset set scoring and note P1 won set
                if ((setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] < 5) || setScores[newMatchViewModel.P1gameIndex] == 7 && setScores[newMatchViewModel.P2gameIndex] < 6){
                    finishSet(1, view)
                }
            }

            //if both players have 6 games won, start set tiebreak
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                tiebreak(1)
                //if p1 won tiebreak, reset set scoring and note p1 won set
                if (newMatchViewModel.P1tiebreakScore > 6 && (newMatchViewModel.P1tiebreakScore - newMatchViewModel.P2tiebreakScore) > 1){

                    finishSet(1, view)

                }
            }
            else{
                //otherwise note that a point was scored for P1 and increase game score
                pointScored(1)
            }


        }


        btnP2Score.setOnClickListener {
            //when player one button is pressed, note that a serve must be hit
            ServesHit()

            //if both players won a set, start condition for third set tiebreak
            if (setP1.text == "1" && setP2.text == "1"){
                tiebreak(2)

                //if p2 won the tiebreak, end the tiebreak and finish  match
                if (newMatchViewModel.P2tiebreakScore > 9 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    newMatchViewModel.setIndexP2 += 1
                    finishSet(2, view)
                }
            }

            //if the game has been scored, reset game scoring and add one game scored to P2
            else if (newMatchViewModel.P2index == 4) {
                gameScored(2)

                //if total game scored for p2 is enough for set, reset set scoring and note P1 won set
                if ((setScores[newMatchViewModel.P2gameIndex] == 6 && setScores[newMatchViewModel.P1gameIndex] < 5) || setScores[newMatchViewModel.P2gameIndex] == 7 && setScores[newMatchViewModel.P1gameIndex] < 6){
                    finishSet(2, view)
                }

            }
            //if both players have 6 games won, start set tiebreak
            else if (setScores[newMatchViewModel.P1gameIndex] == 6 && setScores[newMatchViewModel.P2gameIndex] == 6){
                tiebreak(2)
                //if p2 won tiebreak, reset set scoring and note p2 won set
                if (newMatchViewModel.P2tiebreakScore > 6 && (newMatchViewModel.P2tiebreakScore - newMatchViewModel.P1tiebreakScore) > 1){
                    finishSet(2, view)
                }
            }
            else{
                //otherwise note that a point was scored for P2 and increase game score
                pointScored(2)
            }
        }


        btnFirstServeMiss.setOnClickListener{
            //button for if the server has missed the first serve
            if (newMatchViewModel.FirstServer == 1){
                newMatchViewModel.P1TotalFirstServeMissed += 1
            }
            else if (newMatchViewModel.FirstServer == 2){
                newMatchViewModel.P2TotalFirstServeMissed += 1
            }
            ServesHit()
        }


        btnSecondServeMiss.setOnClickListener {
            //button for second serve missed, when clicked it automatically gives point to the returner by calling score button

            //if firtserver is player 2, then give point to P1
            if (newMatchViewModel.FirstServer == 2) {
                newMatchViewModel.P2TotalSecondServeMissed +=1
                btnP1Score.performClick()
            }
            //if firstserver is player 1, then give point to P2
            else if (newMatchViewModel.FirstServer == 1) {
                newMatchViewModel.P1TotalSecondServeMissed +=1
                btnP2Score.performClick()
            }
        }


        btnReturnMiss.setOnClickListener {
            //button for if the returner misses the serve

            //if the returner is P2, then give P1 a point
            if (newMatchViewModel.FirstServer == 1) {
                newMatchViewModel.P2TotalReturnMissed +=1
                btnP1Score.performClick()
            }

            //if returner is P1, then give P2 a point
            else if (newMatchViewModel.FirstServer == 2) {
                newMatchViewModel.P1TotalReturnMissed +=1
                btnP2Score.performClick()
            }
        }

    }

    public fun pointScored(i:Int){
        //function for when a point is scored, takes in integer i denoting if P1 or P2 won the point
        if (i == 1){
            //reset score text to new score
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            newMatchViewModel.P1index += 1
            newMatchViewModel.P1totalpoints +=1
        }
        else if (i==2){
            //reset score text to new score
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            newMatchViewModel.P2index += 1
            newMatchViewModel.P2totalpoints +=1
        }
        //increment total points played
        newMatchViewModel.totalpoints +=1
    }


    public fun gameScored(i:Int){
        //function for if game is won,, taking in intger i denoting if P1 or P2 won the game
        if (i==1){
            //reset the points and update score on the textviews
            pointsReset()
            newMatchViewModel.P1gameIndex +=1
            gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            newMatchViewModel.P2index +=1
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            //increment P1index
            newMatchViewModel.P1index+=1
            //increment P1 total points won
            newMatchViewModel.P1totalpoints +=1

        }
        else if (i==2){
            //reset the points and update score on the textviews
            pointsReset()
            newMatchViewModel.P2gameIndex +=1
            gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()
            scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
            newMatchViewModel.P1index +=1
            scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
            //increment p2 index and p2 total points won
            newMatchViewModel.P2index+=1
            newMatchViewModel.P2totalpoints +=1
        }
        //increment total points played
        newMatchViewModel.totalpoints +=1
        //change the server now game is done
        changeServer()
    }

    public fun pointsReset (){
        //reset the indexes that keep track of the points
        newMatchViewModel.P2index = 0
        newMatchViewModel.P1index = 0
    }
    public fun gamesReset(){
        //reset game indexes that keep track of games won
        newMatchViewModel.P1gameIndex = 0
        newMatchViewModel.P2gameIndex = 0
    }

    public fun tiebreak(i:Int){
        //tiebreak function that takes in integer i denoting if p1 or p2 won the point
        if (i == 1){
            //increase tiebreak score for p1 and set that as the textview
            newMatchViewModel.P1tiebreakScore +=1
            scoreP1.text = newMatchViewModel.P1tiebreakScore.toString()
            //increment total points won for p1
            newMatchViewModel.P1totalpoints += 1
        }
        else if (i == 2){
            //increase teibreak score for p2 and set that as the textview
            newMatchViewModel.P2tiebreakScore +=1
            scoreP2.text = newMatchViewModel.P2tiebreakScore.toString()
            //increment total points won for p2
            newMatchViewModel.P2totalpoints += 1
        }
        //increment total points played
        newMatchViewModel.totalpoints += 1
        //after first point of the tiebreak, change server per tennis rules
        if ((newMatchViewModel.P1tiebreakScore == 1 && newMatchViewModel.P2tiebreakScore == 0) || (newMatchViewModel.P2tiebreakScore == 1 && newMatchViewModel.P1tiebreakScore == 0)) {
            changeServer()
        }
        //after first point, change server after every other point
        else if ((((newMatchViewModel.P1tiebreakScore + newMatchViewModel.P2tiebreakScore) - 1) % 2) == 0){
            changeServer()
        }

    }

    public fun finishSet(i:Int, view:View){
        //finish set function taking in integer i denoting who won the set and view
        //reset points
        pointsReset()

        //keep track of what the set scores were, if first set, track set1 score
        if (newMatchViewModel.setIndexP1 == 0 && newMatchViewModel.setIndexP2==0){
            newMatchViewModel.Set1P1 = setScores[newMatchViewModel.P1gameIndex].toString()
            newMatchViewModel.Set1P2 = setScores[newMatchViewModel.P2gameIndex].toString()
            //if set tiebreak took place, set winner to 7 instead of 6
            if (newMatchViewModel.P1tiebreakScore > newMatchViewModel.P2tiebreakScore){
                newMatchViewModel.Set1P1 = "7"
            }
            else if (newMatchViewModel.P1tiebreakScore < newMatchViewModel.P2tiebreakScore){
                newMatchViewModel.Set1P2 = "7"
            }
        }
        //for second set, track under set2 score
        else if (newMatchViewModel.setIndexP1 + newMatchViewModel.setIndexP2 == 1){
            newMatchViewModel.Set2P1 = setScores[newMatchViewModel.P1gameIndex].toString()
            newMatchViewModel.Set2P2 = setScores[newMatchViewModel.P2gameIndex].toString()
            //if set tiebreak took place, set winner to 7 insetad of 6
            if (newMatchViewModel.P1tiebreakScore > newMatchViewModel.P2tiebreakScore){
                newMatchViewModel.Set2P1 = "7"
            }
            else if (newMatchViewModel.P1tiebreakScore < newMatchViewModel.P2tiebreakScore){
                newMatchViewModel.Set2P2 = "7"
            }
        }
        //if a set 3 took place, put the tiebreak score saved to set3
        else {
            newMatchViewModel.Set3P1 = newMatchViewModel.P1tiebreakScore.toString()
            newMatchViewModel.Set3P2 = newMatchViewModel.P2tiebreakScore.toString()
        }

        //reset games
        gamesReset()

        //update set scoring
        gameP1.text = setScores[newMatchViewModel.P1gameIndex].toString()
        gameP2.text = setScores[newMatchViewModel.P2gameIndex].toString()

        if (i == 1){
            newMatchViewModel.setIndexP1 += 1
            if (newMatchViewModel.setIndexP1 == 1){
                //if first set won, update scoring to denote first set won for p1
                setP1.text = "1"
            }
            else{
                //if second set won, pull up the match sum fragment
                Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
            }
        }
        else if (i == 2){
            newMatchViewModel.setIndexP2 += 1
            if (newMatchViewModel.setIndexP2 == 1){
                //if first set won, update scoring to denote first set won for p2
                setP2.text = "1"
            }
            else{
                //if second set won, pull up the match sum fragment
                //setP2.text = "MATCH IS DONE"
                Navigation.findNavController(view).navigate(R.id.action_matchScoringFrag_to_matchSumFrag)
            }


        }
        //reset tiebreak scoring, update score textview
        newMatchViewModel.P1tiebreakScore = 0
        newMatchViewModel.P2tiebreakScore = 0
        scoreP1.text = gameScores[newMatchViewModel.P1index].toString()
        scoreP2.text = gameScores[newMatchViewModel.P2index].toString()
        newMatchViewModel.P1index = 1
        newMatchViewModel.P2index = 1
    }
    public fun changeServer(){
        //change server function which swaps the current server denoted FirstServer
        if (newMatchViewModel.FirstServer == 1){
            newMatchViewModel.FirstServer = 2
        }
        else{
            newMatchViewModel.FirstServer = 1
        }
    }
    public fun ServesHit(){
        //servesHit function which tracks total number of serves hit for the current server
        if (newMatchViewModel.FirstServer ==1){
            newMatchViewModel.P1ServesTotal += 1
        }
        else if (newMatchViewModel.FirstServer ==2){
            newMatchViewModel.P2ServesTotal +=1
        }
    }
}