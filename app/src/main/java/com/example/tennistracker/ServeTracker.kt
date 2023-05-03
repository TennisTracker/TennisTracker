package com.example.tennistracker

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
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

            true
        }

        //val imageView = findViewById<ImageView>(R.id.my_image)
        //imageView.setOnClickListener { event ->

            //onClick(imageView)
            //val mX = event.getX()
            //val mY = event.getY()
            //coordinateList.add(Pair(x, y))


            //System.out.println(mX)
            //System.out.println(mY)
/*
            val dotView = View(this)
            dotView.setBackgroundResource(R.drawable.dot)
            val dotSize = resources.getDimensionPixelSize(R.dimen.dot_size)

            // Calculate the position of the dot relative to the parent view
            val parentView = imageView.parent as ViewGroup
            val imageViewX = imageView.getX()
            val imageViewY = imageView.getY()
            val dotX = imageViewX + x - dotSize / 2
            val dotY = imageViewY + y - dotSize / 2

            // Set the position and size of the dot view
            val layoutParams = ViewGroup.LayoutParams(dotSize, dotSize)
            dotView.layoutParams = layoutParams
            dotView.x = dotX
            dotView.y = dotY

            // Add the dot to the parent view and animate its alpha value
            parentView.addView(dotView)
            dotView.alpha = 1.0f
            dotView.animate()
                .alpha(0.0f)
                .setDuration(3000)
                .withEndAction {
                    parentView.removeView(dotView)
                }
*/

        //}



    }

    public fun onClick(view: View){
        val values = IntArray(2)
        view.getLocationOnScreen(values)
        System.out.println(values[0])
        System.out.println(values[1])
    }


}

