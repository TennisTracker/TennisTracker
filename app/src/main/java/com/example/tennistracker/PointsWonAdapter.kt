package com.example.tennistracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PointsWonAdapter(private val pointsWonList: ArrayList<Player>): RecyclerView.Adapter<PointsWonAdapter.PointsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.points_won_item,
        parent, false)
        return PointsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pointsWonList.size
    }

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        val currentPlayer = pointsWonList[position]

        holder.playerName.text = currentPlayer.name.toString()
        holder.pointsWon.text = currentPlayer.pointsWon.toString()
    }


    class PointsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val playerName : TextView = itemView.findViewById(R.id.name_points_won)
        val pointsWon : TextView = itemView.findViewById(R.id.points_points_won)

    }
}