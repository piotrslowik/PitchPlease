package com.example.pitchplease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class AnswerWrong : AppCompatActivity() {
    private var timeTotal: Long = 0
    private var correctAnswers: Int = 0
    private var questionNumber: Int = 0
    private var difficulty: Int = 0
    private var scoreTotal: Long = 0
    private var chord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_wrong)
        timeTotal = intent.getLongExtra("timeTotal", 0)
        correctAnswers = intent.getIntExtra("correctAnswers", 0)
        questionNumber = intent.getIntExtra("questionNumber", 0)
        difficulty = intent.getIntExtra("difficulty", 1)
        scoreTotal = intent.getLongExtra("scoreTotal", 0)
        chord = intent.getStringExtra("chord").toString()
        setChordName()
    }

    private fun setChordName() {
        val box: TextView = findViewById<TextView>(R.id.chord_answer_wrong)
        box.text = "TO BYŁ ${chord}"
    }

    fun nextQuestion(view: View) {
        val intent: Intent = if (questionNumber == 10)
            Intent(this, QuizFinish::class.java)
        else
            Intent(this, QuizQuestion::class.java)
        intent.putExtra("timeTotal", timeTotal)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("questionNumber", questionNumber)
        intent.putExtra("difficulty", difficulty)
        intent.putExtra("scoreTotal", scoreTotal)
        startActivity(intent)
        finish()
    }
}