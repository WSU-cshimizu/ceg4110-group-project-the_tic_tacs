package com.example.catmath

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.animation.ObjectAnimator;





class MathDrills: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mathdrills)


        val mainDisplay = findViewById<TextView>(R.id.textView)
        val fourButton = findViewById<Button>(R.id.button4)
        val fiveButton = findViewById<Button>(R.id.button5)
        val sixButton = findViewById<Button>(R.id.button6)
        val sevenButton = findViewById<Button>(R.id.button7)
        val eightButton = findViewById<Button>(R.id.button8)
        val nineButton = findViewById<Button>(R.id.button9)
        val tenButton = findViewById<Button>(R.id.button10)
        val elevenButton = findViewById<Button>(R.id.button11)
        val twelveButton = findViewById<Button>(R.id.button12)
        val thirteenButton = findViewById<Button>(R.id.button13)
        val clearButton = findViewById<Button>(R.id.button)
        val enterButton = findViewById<Button>(R.id.button3)


        fourButton.setOnClickListener {
            val number = 1;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }
        fiveButton.setOnClickListener {
            val number = 2;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        sixButton.setOnClickListener {
            val number = 3;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        sevenButton.setOnClickListener {
            val number = 4;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        eightButton.setOnClickListener {
            val number = 5;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        nineButton.setOnClickListener {
            val number = 6;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        tenButton.setOnClickListener {
            val number = 9;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        elevenButton.setOnClickListener {
            val number = 8;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        twelveButton.setOnClickListener {
            val number = 7;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }

        thirteenButton.setOnClickListener {
            val number = 0;
            Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
        }



         fun moveButtonRightToLeft(button: Button) {
            // ObjectAnimator for a smooth move from right to left
            val animator = ObjectAnimator.ofFloat(button, "translationX", 1000f, 0f)
            animator.duration = 3000 // Duration of the animation in milliseconds
            animator.repeatCount = ObjectAnimator.INFINITE // Repeat the animation infinitely
            animator.repeatMode = ObjectAnimator.REVERSE // Make the button move back and forth
            animator.start()
        }

        moveButtonRightToLeft(fourButton)
        moveButtonRightToLeft(fiveButton)
        moveButtonRightToLeft(sixButton)
        moveButtonRightToLeft(sevenButton)
        moveButtonRightToLeft(eightButton)
        moveButtonRightToLeft(nineButton)
        moveButtonRightToLeft(tenButton)
        moveButtonRightToLeft(elevenButton)
        moveButtonRightToLeft(twelveButton)
        moveButtonRightToLeft(thirteenButton)
    }

}