package com.example.tennistracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class ServeTracker: AppCompatActivity() {

    private val coordinateList = mutableListOf<Pair<Float, Float>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_servespot_tracker)

        val imageView = findViewById<ImageView>(R.id.my_image)
        imageView.setOnClickListener { event ->
            val x = event.getX()
            val y = event.getY()
            coordinateList.add(Pair(x, y))

            System.out.println(x)
            System.out.println(y)
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
        }



    }



}

