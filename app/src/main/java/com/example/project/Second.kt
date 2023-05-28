package com.example.project

import MusicService
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private var counter1 = 0
private var counter2 = 0
private var counter3 = 0
private var counter4= 0


class Second : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val myColor = getColor(R.color.background_color)
        window.decorView.setBackgroundColor(myColor)

        startService(Intent(this, MusicService::class.java))

        val cancBT = findViewById<Button>(R.id.canid)
        val procBT = findViewById<Button>(R.id.procid)

        val counterTextView1 = findViewById<TextView>(R.id.counterTextView1)
        val plusButton1 = findViewById<Button>(R.id.plusButton1)
        val minusButton1 = findViewById<Button>(R.id.minusButton1)

        val counterTextView2 = findViewById<TextView>(R.id.counterTextView2)
        val plusButton2 = findViewById<Button>(R.id.plusButton2)
        val minusButton2 = findViewById<Button>(R.id.minusButton2)

        val counterTextView3 = findViewById<TextView>(R.id.counterTextView3)
        val plusButton3 = findViewById<Button>(R.id.plusButton3)
        val minusButton3 = findViewById<Button>(R.id.minusButton3)

        val counterTextView4 = findViewById<TextView>(R.id.counterTextView4)
        val plusButton4 = findViewById<Button>(R.id.plusButton4)
        val minusButton4 = findViewById<Button>(R.id.minusButton4)

        plusButton1.setOnClickListener {
            counter1++
            counterTextView1.text = counter1.toString()
        }

        minusButton1.setOnClickListener {
            if (counter1 > 0) {
                counter1--
                counterTextView1.text = counter1.toString()
            }
        }

        plusButton2.setOnClickListener {
            counter2++
            counterTextView2.text = counter2.toString()
        }

        minusButton2.setOnClickListener {
            if (counter2 > 0) {
                counter2--
                counterTextView2.text = counter2.toString()
            }
        }

        plusButton3.setOnClickListener {
            counter3++
            counterTextView3.text = counter3.toString()
        }

        minusButton3.setOnClickListener {
            if (counter3 > 0) {
                counter3--
                counterTextView3.text = counter3.toString()
            }
        }

        plusButton4.setOnClickListener {
            counter4++
            counterTextView4.text = counter4.toString()
        }

        minusButton4.setOnClickListener {
            if (counter4 > 0) {
                counter4--
                counterTextView4.text = counter4.toString()
            }
        }

cancBT.setOnClickListener{
    counterTextView1.text= "0"
    counterTextView2.text = "0"
    counterTextView3.text = "0"
    counterTextView4.text = "0"

}

        procBT.setOnClickListener{
            val intent = Intent(this, Final::class.java)
            startActivity(intent)
        }












    }
    object Prices{
        var sum: Int = (5*counter1) + (7*counter2) + (4*counter3) + (10*counter4)
        var burgnum: Int = counter1
        var pizzanum:Int = counter2
        var hotdognum:Int = counter3
        var pastanum :Int = counter4
    }
}
