package com.example.tennistracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistracker.databinding.ActivityPrevMatchesBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PrevMatches : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityPrevMatchesBinding
    private val PrevMatchViewModel: PrevMatchViewModel by viewModels()
    private lateinit var prevRecyclerView: RecyclerView
    private lateinit var prevMatchArrayList: ArrayList<Match>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrevMatchesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Get the number of matches in database
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child("numMatches").get().addOnSuccessListener {
            if(it.exists()){
                PrevMatchViewModel.numMatches = it.child("matches").getValue(Int::class.java)!!
            }
        }

        //Set the text view on screen
        updateItem1(PrevMatchViewModel.pageIndex)
        updateItem2(PrevMatchViewModel.pageIndex)
        updateItem3(PrevMatchViewModel.pageIndex)
        updateItem4(PrevMatchViewModel.pageIndex)
        updateItem5(PrevMatchViewModel.pageIndex)

        binding.nextPageButton.setOnClickListener {
            if(PrevMatchViewModel.pageIndex <= (PrevMatchViewModel.numMatches / 5)+1) {
                PrevMatchViewModel.pageIndex += 1
                updateItem1(PrevMatchViewModel.pageIndex)
                updateItem2(PrevMatchViewModel.pageIndex)
                updateItem3(PrevMatchViewModel.pageIndex)
                updateItem4(PrevMatchViewModel.pageIndex)
                updateItem5(PrevMatchViewModel.pageIndex)
            }
        }
        binding.prevPageButton.setOnClickListener {
            if(PrevMatchViewModel.pageIndex!=0) {
                PrevMatchViewModel.pageIndex -= 1
                updateItem1(PrevMatchViewModel.pageIndex)
                updateItem2(PrevMatchViewModel.pageIndex)
                updateItem3(PrevMatchViewModel.pageIndex)
                updateItem4(PrevMatchViewModel.pageIndex)
                updateItem5(PrevMatchViewModel.pageIndex)
            }
        }


    }

    fun updateItem1(index:Int){
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child((index*5).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                binding.game1Players.text = p1name + " Vs "+ p2name
                binding.date1.text = date
            }
            else{
                binding.game1Players.text = ""
                binding.date1.text = ""

            }


        }

    }
    fun updateItem2(index:Int){
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5)+1).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                binding.game2Players.text = p1name + " Vs "+ p2name
                val date = it.child("dateAndTime").getValue(String::class.java)
                binding.game2Players.text = p1name + " Vs "+ p2name
                binding.date2.text = date
            }
            else{
                binding.game2Players.text = ""
                binding.date2.text = ""
            }


        }

    }
    fun updateItem3(index:Int){
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5)+2).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                binding.date3.text = date
                binding.game3Players.text = p1name + " Vs "+ p2name
            }
            else{
                binding.date3.text = ""
                binding.game3Players.text = ""
            }


        }

    }
    fun updateItem4(index:Int){
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5)+3).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                val date = it.child("dateAndTime").getValue(String::class.java)
                binding.date4.text = date
                binding.game4Players.text = p1name + " Vs "+ p2name
            }
            else{
                binding.game4Players.text = ""
                binding.date4.text = ""
            }


        }

    }
    fun updateItem5(index:Int){
        database = FirebaseDatabase.getInstance().getReference("Players")
        database.child(((index*5)+4).toString()).get().addOnSuccessListener {
            if(it.exists()){
                val p1name = it.child("p1name").getValue(String::class.java)
                val p2name = it.child("p2name").getValue(String::class.java)
                binding.game5Players.text = p1name + " Vs "+ p2name
                val date = it.child("dateAndTime").getValue(String::class.java)
                binding.date5.text = date
            }
            else{
                binding.game5Players.text = ""
                binding.date5.text = ""
            }


        }

    }





}