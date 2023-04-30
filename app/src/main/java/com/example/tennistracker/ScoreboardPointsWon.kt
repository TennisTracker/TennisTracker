package com.example.tennistracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ScoreboardPointsWon : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var playerRecyclerView: RecyclerView
    private lateinit var playerArrayList: ArrayList<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboardpointswon)

        playerRecyclerView = findViewById(R.id.pointsWon2)
        playerRecyclerView.layoutManager = LinearLayoutManager(this)

        playerRecyclerView.setHasFixedSize(true)
        playerArrayList = arrayListOf<Player>()
        getPlayerData()
        playerRecyclerView.adapter = PointsWonAdapter(playerArrayList)


    }

        private fun getPlayerData(){

            database = FirebaseDatabase.getInstance().getReference("Player")

            database.addValueEventListener(object: ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {

                    if(snapshot.exists()){

                        for(playerSnapshot in snapshot.children){

                            val player = playerSnapshot.getValue(Player::class.java)
                            playerArrayList?.add(player!!)

                        }

                        //playerRecyclerView.adapter = PointsWonAdapter(playerArrayList)

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }

}