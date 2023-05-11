package com.example.tennistracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistracker.databinding.ActivityPrevMatchesBinding
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
    }
}