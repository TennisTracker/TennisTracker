package com.example.tennistracker

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class ServeTrackerSum: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servespot_tracker_sum)

        val mRelativeLayout = findViewById<RelativeLayout>(R.id.relative_layout_2)



        for (pair in coordinateList) {
            placeDot(pair.first, pair.second, mRelativeLayout)
        }


    }


    public fun placeDot(x: Float, y: Float, parentView: ViewGroup) {
        // Create a new ImageView for the dot
        val dot = ImageView(this)
        dot.setImageResource(R.drawable.dot)
        dot.setColorFilter(Color.RED)
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



