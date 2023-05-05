package com.example.tennistracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

public val coordinateList = mutableListOf<Pair<Float, Float>>()
class ServeTracker: AppCompatActivity() {
=======
    private val coordinateList = mutableListOf<Triple<Float, Float, Boolean>>()
    var returnMade: Boolean = true



    private val handler = Handler(Looper.getMainLooper())
    private val dotDisappearDelayMs = 2000L
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_servespot_tracker)

        val endTracker = findViewById<Button>(R.id.end_tracker)

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


            placeDot(mX.toInt(), mY.toInt(), mRelativeLayout)
            showPopupWindow()
            coordinateList.add(Triple(mX, mY, returnMade))
            true

        }

        endTracker.setOnClickListener {
            val Intent = Intent(this, ServeTrackerSum::class.java)
            startActivity(Intent)
        }



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

    private var popupWindow: PopupWindow? = null

    private fun showPopupWindow() {
        if (popupWindow == null) {
            // Inflate the popup layout
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.popup_window, null)

            // Create the PopupWindow object
            popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            // Set a background drawable for the PopupWindow
            popupWindow?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // Set a click listener for the buttons
            val buttonOption1 = popupView.findViewById<Button>(R.id.button_option1)
            val buttonOption2 = popupView.findViewById<Button>(R.id.button_option2)
            buttonOption1.setOnClickListener {
                returnMade = true
                popupWindow?.dismiss()
            }
            buttonOption2.setOnClickListener {
                returnMade = false
                popupWindow?.dismiss()
            }
        }

        // Show the PopupWindow at the center of the screen
        popupWindow?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
    }


}

