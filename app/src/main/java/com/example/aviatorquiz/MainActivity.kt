package com.example.aviatorquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.aviatorquiz.Result
import com.google.gson.Gson
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var questions = listOf<Question>()
    private var currentQuestionIndex = 0
    private var score = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val back_arrow = findViewById<ImageView>(R.id.back_arrow)

        // Load questions from JSON file
        val inputStream = assets.open("questions.json").bufferedReader().use { it.readText() }
        val questions = Gson().fromJson(inputStream, Array<Question>::class.java).toList()

        // Shuffle questions
        this.questions = questions.shuffled()

        // Display the first question
        showQuestion(currentQuestionIndex)

        val nextBTN = findViewById<Button>(R.id.next)
        nextBTN.setOnClickListener(){

            val optionA = findViewById<TextView>(R.id.optionA)
            val optionB = findViewById<TextView>(R.id.optionB)
            val optionC = findViewById<TextView>(R.id.optionC)
            val optionD = findViewById<TextView>(R.id.optionD)
            optionA.isEnabled = true
            optionB.isEnabled = true
            optionC.isEnabled = true
            optionD.isEnabled = true

        if (currentQuestionIndex < questions.size - 1) {
            // If there are more questions, show the next one
            currentQuestionIndex++
            showQuestion(currentQuestionIndex)
            nextBTN.isEnabled = false
        }
        else {
            // If there are no more questions, show the score
            val questionText = findViewById<TextView>(R.id.questionText)
            val optionA = findViewById<TextView>(R.id.optionA)
            val optionB = findViewById<TextView>(R.id.optionB)
            val optionC = findViewById<TextView>(R.id.optionC)
            val optionD = findViewById<TextView>(R.id.optionD)
            //val restart = findViewById<Button>(R.id.restartButton)
            questionText.text = "Your score is $score out of ${questions.size}"
            optionA.text = ""
            optionB.text = ""
            optionC.text = ""
            optionD.text = ""
            nextBTN.isEnabled = false
            //restart.visibility = View.VISIBLE
            var intent = Intent(this, Result::class.java)
            intent.putExtra("score", score.toString())
            startActivity(intent)
            finish()
        }
        }
        back_arrow.setOnClickListener(){
            onBackPressed()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun showQuestion(questionIndex: Int) {
        val question = questions[questionIndex]
        val questionnumber = findViewById<TextView>(R.id.questionnumber)
        val questionText = findViewById<TextView>(R.id.questionText)
        val optionA = findViewById<TextView>(R.id.optionA)
        val optionB = findViewById<TextView>(R.id.optionB)
        val optionC = findViewById<TextView>(R.id.optionC)
        val optionD = findViewById<TextView>(R.id.optionD)
        val nextBTN = findViewById<Button>(R.id.next)
        val scoretxt = findViewById<TextView>(R.id.Score_txt)
        optionD.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
        optionC.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
        optionB.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
        optionA.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
        questionText.text = question.Question
        optionA.text = "A. ${question.A}"
        optionB.text = "B. ${question.B}"
        optionC.text = "C. ${question.C}"
        optionD.text = "D. ${question.D}"
        var qs = questionIndex+1
        questionnumber.text = "Q. ${qs.toString()}/"
        optionA.setOnClickListener {
            nextBTN.isEnabled = true
            optionD.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionC.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionB.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionB.isEnabled = false
            optionC.isEnabled = false
            optionD.isEnabled = false

            val question = questions[currentQuestionIndex]
            if (optionA.text[0].toString()==question.Answer){
                optionA.setBackgroundDrawable(getDrawable(R.drawable.correct_bg))
                score = score+10
            }else{
                optionA.setBackgroundDrawable(getDrawable(R.drawable.wrong_bg))
            }
        }
        optionB.setOnClickListener {
            nextBTN.isEnabled = true

            optionD.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionC.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionA.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))

            val question = questions[currentQuestionIndex]
            if (optionB.text[0].toString()==question.Answer){
                optionB.setBackgroundDrawable(getDrawable(R.drawable.correct_bg))
                score = score+10
            }else{
                optionB.setBackgroundColor(getColor(R.color.red))
                optionB.setBackgroundDrawable(getDrawable(R.drawable.wrong_bg))

            }

            optionA.isEnabled = false
            optionC.isEnabled = false
            optionD.isEnabled = false

        }
        optionC.setOnClickListener {
            nextBTN.isEnabled = true

            optionD.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionB.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionA.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionA.isEnabled = false
            optionB.isEnabled = false
            optionD.isEnabled = false

            val question = questions[currentQuestionIndex]
            if (optionC.text[0].toString()==question.Answer){
                optionC.setBackgroundDrawable(getDrawable(R.drawable.correct_bg))
                score = score+10
            }else{
                optionC.setBackgroundDrawable(getDrawable(R.drawable.wrong_bg))
            }
        }
        optionD.setOnClickListener {
            nextBTN.isEnabled = true
            optionC.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionB.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionA.setBackgroundDrawable(getDrawable(R.drawable.normal_bg))
            optionA.isEnabled = false
            optionB.isEnabled = false
            optionC.isEnabled = false

            val question = questions[currentQuestionIndex]
            if (optionD.text[0].toString()==question.Answer){
                optionD.setBackgroundDrawable(getDrawable(R.drawable.correct_bg))
                score = score+10
            }else{
                optionD.setBackgroundDrawable(getDrawable(R.drawable.wrong_bg))
            }

        }
        scoretxt.text = score.toString()
    }

}