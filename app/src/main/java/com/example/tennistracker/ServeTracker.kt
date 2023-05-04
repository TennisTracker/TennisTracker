package com.example.tennistracker

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class ServeTracker: AppCompatActivity() {
    private val coordinateList = mutableListOf<Pair<Float, Float>>()

    private val handler = Handler(Looper.getMainLooper())
    private val dotDisappearDelayMs = 2000L
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_servespot_tracker)

        val mRelativeLayout = findViewById<RelativeLayout>(R.id.relative_layout_1)
        val mTextViewX = findViewById<TextView>(R.id.text_view_1)
        val mTextViewY = findViewById<TextView>(R.id.text_view_2)

        mRelativeLayout.setOnTouchListener { _, motionEvent ->

            // X and Y values are fetched
            val mX = motionEvent.x
            val mY = motionEvent.y

            // X and Y values are
            // displayed in the TextView
            mTextViewX.text = "X: $mX"
            mTextViewY.text = "Y: $mY"
            coordinateList.add(Pair(mX, mY))

            placeDot(mX.toInt(), mY.toInt(), mRelativeLayout)

            true

        }



    }

    public fun onClick(view: View){
        val values = IntArray(2)
        view.getLocationOnScreen(values)
        System.out.println(values[0])
        System.out.println(values[1])
    }


    private fun placeDot(x: Int, y: Int, parentView: ViewGroup) {
        // Create a new ImageView for the dot
        val dot = ImageView(this)
        dot.setImageResource(R.drawable.dot)
        dot.setColorFilter(Color.RED)
        val dotSize = 30
        val params = RelativeLayout.LayoutParams(dotSize, dotSize)
        params.leftMargin = x - dotSize / 2
        params.topMargin = y - dotSize / 2
        dot.layoutParams = params
        dot.scaleType = ImageView.ScaleType.CENTER_CROP

        // Add the dot to the parent view
        parentView.addView(dot)

        // Schedule the dot to disappear after a short delay
        handler.postDelayed({
            parentView.removeView(dot)
        }, dotDisappearDelayMs)
    }


}

