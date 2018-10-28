package com.fgran.memorygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_end_game.*

class EndGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)
        if (intent.getBooleanExtra("victory", false)) {
            textView7.text = "Vitoria"
        } else {
            textView7.text = "Derrota"
        }
        button2.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
