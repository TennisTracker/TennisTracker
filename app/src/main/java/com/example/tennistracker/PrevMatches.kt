package com.example.tennistracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class PrevMatches : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var prevRecyclerView: RecyclerView
    private lateinit var prevMatchArrayList: ArrayList<Match>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_matches)

        prevRecyclerView = findViewById(R.id.prevMatchRecycler)
        prevRecyclerView.layoutManager = LinearLayoutManager(this)
        prevRecyclerView.setHasFixedSize(true)
        prevMatchArrayList = arrayListOf<Match>()
        //some database function here!!!

        prevRecyclerView.adapter = PrevMatchesAdapter(prevMatchArrayList)


    }
}