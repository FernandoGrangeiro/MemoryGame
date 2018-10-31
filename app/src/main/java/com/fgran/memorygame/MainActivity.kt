package com.fgran.memorygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDificultyClick()
    }


    private fun setDificultyClick() {
        DificultyEasy.setOnClickListener {
            selected = 1
            setTextColor(first = true)
        }

        DificultyMedium.setOnClickListener {
            selected = 2
            setTextColor(second = true)

        }

        DificultyHard.setOnClickListener {
            selected = 3
            setTextColor()
        }

        buttonPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java).apply {
                putExtra("dificulty", selected)
            })
        }
    }

    private fun setTextColor(first: Boolean = false, second: Boolean = false) {
        when {
            first -> {
                DificultyEasy.setTextColor(ContextCompat.getColor(this,R.color.yellow))
                DificultyMedium.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
                DificultyHard.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

            }
            second -> {
                DificultyEasy.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
                DificultyMedium.setTextColor(ContextCompat.getColor(this,R.color.yellow))
                DificultyHard.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

            }
            else -> {
                DificultyEasy.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
                DificultyMedium.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
                DificultyHard.setTextColor(ContextCompat.getColor(this,R.color.yellow))
            }
        }
    }
}
