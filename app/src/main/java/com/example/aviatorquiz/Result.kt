package com.example.aviatorquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score_show = findViewById<TextView>(R.id.score_result_txt)
        val playAgain = findViewById<TextView>(R.id.play_again)
        val share = findViewById<TextView>(R.id.share)
        val backArrow = findViewById<ImageView>(R.id.back_arrow_result)
        val score = intent.getStringExtra("score")
        score_show.text = score
        share.setOnClickListener(){

        }
        playAgain.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        backArrow.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}