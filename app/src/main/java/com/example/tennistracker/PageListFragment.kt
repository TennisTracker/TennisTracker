package com.example.tennistracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageListFragment : Fragment() {

    private val PrevMatchViewModel: PrevMatchViewModel by activityViewModels()
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get the number of matches in database
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child("numMatches").get().addOnSuccessListener {
            if(it.exists()){
                PrevMatchViewModel.numMatches = it.child("matches").getValue(Int::class.java)!!
            }
        }


        val nextPageButton = view.findViewById<Button>(R.id.next_page_button)
        val prevPageButton = view.findViewById<Button>(R.id.prev_page_button)
        val Button1 = view.findViewById<Button>(R.id.button1)
        val Button2 = view.findViewById<Button>(R.id.button2)
        val Button3 = view.findViewById<Button>(R.id.button3)
        val Button4 = view.findViewById<Button>(R.id.button4)
        val Button5 = view.findViewById<Button>(R.id.button5)

        val game5Players = view.findViewById<TextView>(R.id.game5Players)
        val pageCounter = view.findViewById<TextView>(R.id.pageCounter)

        updateItem1(PrevMatchViewModel.pageIndex, view)
        updateItem2(PrevMatchViewModel.pageIndex, view)
        updateItem3(PrevMatchViewModel.pageIndex, view)
        updateItem4(PrevMatchViewModel.pageIndex, view)
        updateItem5(PrevMatchViewModel.pageIndex, view)

        nextPageButton.setOnClickListener {
            if(game5Players.text != "") {
                PrevMatchViewModel.pageIndex += 1
                pageCounter.text = PrevMatchViewModel.pageIndex.toString()
                updateItem1(PrevMatchViewModel.pageIndex, view)
                updateItem2(PrevMatchViewModel.pageIndex, view)
                updateItem3(PrevMatchViewModel.pageIndex, view)
                updateItem4(PrevMatchViewModel.pageIndex, view)
                updateItem5(PrevMatchViewModel.pageIndex, view)
            }
        }
        prevPageButton.setOnClickListener {
            if(PrevMatchViewModel.pageIndex!=0) {
                PrevMatchViewModel.pageIndex -= 1
                pageCounter.text = PrevMatchViewModel.pageIndex.toString()
                updateItem1(PrevMatchViewModel.pageIndex, view)
                updateItem2(PrevMatchViewModel.pageIndex, view)
                updateItem3(PrevMatchViewModel.pageIndex, view)
                updateItem4(PrevMatchViewModel.pageIndex, view)
                updateItem5(PrevMatchViewModel.pageIndex, view)
            }
        }

        //Have the buttons go to prev match stats page
        Button1.setOnClickListener {
            PrevMatchViewModel.prevMatchIndex = (PrevMatchViewModel.pageIndex * 5)
            Navigation.findNavController(view).navigate(R.id.action_pageListFragment_to_prevMatchStatsFragment)
        }
        Button2.setOnClickListener {
            PrevMatchViewModel.prevMatchIndex = (PrevMatchViewModel.pageIndex * 5) + 1
            Navigation.findNavController(view).navigate(R.id.action_pageListFragment_to_prevMatchStatsFragment)
        }
        Button3.setOnClickListener {
            PrevMatchViewModel.prevMatchIndex = (PrevMatchViewModel.pageIndex * 5) + 2
            Navigation.findNavController(view).navigate(R.id.action_pageListFragment_to_prevMatchStatsFragment)
        }
        Button4.setOnClickListener {
            PrevMatchViewModel.prevMatchIndex = (PrevMatchViewModel.pageIndex * 5) + 3
            Navigation.findNavController(view).navigate(R.id.action_pageListFragment_to_prevMatchStatsFragment)
        }
        Button5.setOnClickListener {
            PrevMatchViewModel.prevMatchIndex = (PrevMatchViewModel.pageIndex * 5) + 4
            Navigation.findNavController(view).navigate(R.id.action_pageListFragment_to_prevMatchStatsFragment)
        }




    }
    fun updateItem1(index:Int, view: View){
        //Get the view of the player text
        val game1Players = view.findViewById<TextView>(R.id.game1Players)
        val date1 = view.findViewById<TextView>(R.id.date_1)
        val button1 = view.findViewById<Button>(R.id.button1)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child((index*5).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)
                game1Players.text = p1name + " Vs "+ p2name
                date1.text = date + "\t" + score
                button1.visibility = View.VISIBLE
            }
            else{
                game1Players.text = ""
                date1.text = ""
                button1.visibility = View.GONE
            }
        }
    }
    fun updateItem2(index:Int, view: View){
        //Get the view of the player text
        val game2Players = view.findViewById<TextView>(R.id.game2Players)
        val date2 = view.findViewById<TextView>(R.id.date_2)
        val button2 = view.findViewById<Button>(R.id.button2)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5) + 1).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)
                game2Players.text = p1name + " Vs "+ p2name
                date2.text = date + "\t" + score
                button2.visibility = View.VISIBLE
            }
            else{
                game2Players.text = ""
                date2.text = ""
                button2.visibility = View.GONE
            }
        }
    }
    fun updateItem3(index:Int, view: View){
        //Get the view of the player text
        val game3Players = view.findViewById<TextView>(R.id.game3Players)
        val date3 = view.findViewById<TextView>(R.id.date_3)
        val button3 = view.findViewById<Button>(R.id.button3)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5) + 2).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)
                game3Players.text = p1name + " Vs "+ p2name
                date3.text = date + "\t" + score
                button3.visibility = View.VISIBLE
            }
            else{
                game3Players.text = ""
                date3.text = ""
                button3.visibility = View.GONE
            }
        }
    }
    fun updateItem4(index:Int, view: View){
        //Get the view of the player text
        val game4Players = view.findViewById<TextView>(R.id.game4Players)
        val date4 = view.findViewById<TextView>(R.id.date_4)
        val button4 = view.findViewById<Button>(R.id.button4)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5) + 3).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)
                game4Players.text = p1name + " Vs "+ p2name
                date4.text = date + "\t" + score
                button4.visibility = View.VISIBLE
            }
            else{
                game4Players.text = ""
                date4.text = ""
                button4.visibility = View.GONE
            }
        }
    }
    fun updateItem5(index:Int, view: View){
        //Get the view of the player text
        val game5Players = view.findViewById<TextView>(R.id.game5Players)
        val date5 = view.findViewById<TextView>(R.id.date_5)
        val button5 = view.findViewById<Button>(R.id.button5)

        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5) + 4).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                val score = it.child("matchScore").getValue(String::class.java)
                game5Players.text = p1name + " Vs "+ p2name
                date5.text = date + "\t" + score
                button5.visibility = View.VISIBLE
            }
            else{
                game5Players.text = ""
                date5.text = ""
                button5.visibility = View.GONE
            }
        }
    }
}