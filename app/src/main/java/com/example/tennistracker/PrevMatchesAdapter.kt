package com.example.tennistracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PrevMatchesAdapter(private val prevMatchesList: ArrayList<Match>):
RecyclerView.Adapter<PrevMatchesAdapter.PrevMatchesViewHolder> {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.prev_matches_item,
            parent, false)
        return PrevMatchesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return prevMatchesList.size
    }

    override fun onBindViewHolder(holder: PrevMatchesViewHolder, position: Int) {
        val currentMatch = prevMatchesList[position]

        holder.p1Name.text = currentMatch.p1name.toString()
        holder.p2Name.text = currentMatch.p2name.toString()
        holder.p1PointsWon.text = currentMatch.p1pointsWon.toString()
        holder.p2PointsWon.text = currentMatch.p2pointsWon.toString()



    }

    class PrevMatchesViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val p1Name : TextView = itemView.findViewById(R.id.namep1)
        val p2Name : TextView = itemView.findViewById(R.id.namep2)
        val p1PointsWon : TextView = itemView.findViewById(R.id.pointsp1)
        val p2PointsWon : TextView = itemView.findViewById(R.id.pointsp2)
        // add button functionality in here
    }

}