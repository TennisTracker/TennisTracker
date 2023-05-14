package com.example.tennistracker

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class ServeTrackerSum: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servespot_tracker_sum)

        val mRelativeLayout = findViewById<RelativeLayout>(R.id.relative_layout_2)


        //loop through coordinateList and display the locations.
        for (triple in coordinateList) {
            if (triple.third == false){
                //if return was missed, display as red
                placeDot(triple.first, triple.second, mRelativeLayout, "RED")
            }
            else{
                //if return made, display as green
                placeDot(triple.first, triple.second, mRelativeLayout, "GREEN")
            }

        }


    }


    public fun placeDot(x: Float, y: Float, parentView: ViewGroup, color: String) {
        // Create a new ImageView for the dot
        val dot = ImageView(this)
        dot.setImageResource(R.drawable.dot)
        dot.setColorFilter(Color.parseColor(color))
        val dotSize = 30
        val params = RelativeLayout.LayoutParams(dotSize, dotSize)
        params.leftMargin = (x - dotSize / 2).toInt()
        params.topMargin = (y - dotSize / 2).toInt()
        dot.layoutParams = params
        dot.scaleType = ImageView.ScaleType.CENTER_CROP

        // Add the dot to the parent view
        parentView.addView(dot)
    }
}



