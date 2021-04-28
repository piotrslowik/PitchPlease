package com.example.pitchplease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.lang.System.currentTimeMillis

class QuizQuestion : AppCompatActivity() {
    private var timeStart = currentTimeMillis()
    private var timeTotal: Long = 0
    private var correctAnswers: Int = 0
    private var questionNumber: Int = 1
    private var difficulty: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        timeTotal = intent.getIntExtra("timeTotal", 0).toLong()
        correctAnswers = intent.getIntExtra("correctAnswers", 0)
        questionNumber = intent.getIntExtra("questionNumber", 1)
    }

    fun onQuestionClicked() {
        timeStart = currentTimeMillis()
    }

    fun onCorrectAnswer() {
        val time: Long = currentTimeMillis() - timeStart
        timeTotal += time

        var score = 500 - (time / 20)
        if (score < 0) score = 0
        score += 100

        val intent: Intent = if (questionNumber == 10)
            Intent(this, QuizFinish::class.java)
        else
            Intent(this, AnswerCorrect::class.java)
        intent.putExtra("timeTotal", timeTotal)
        intent.putExtra("correctAnswers", correctAnswers++)
        intent.putExtra("score", score)
        intent.putExtra("questionNumber", questionNumber++)
        startActivity(intent)
    }

    fun setAnswerButtons() {
        val buttons = arrayOf(
            findViewById<Button>(R.id.answer_button_quiz_question_1),
            findViewById<Button>(R.id.answer_button_quiz_question_2),
            findViewById<Button>(R.id.answer_button_quiz_question_3),
            findViewById<Button>(R.id.answer_button_quiz_question_4)
        )
    }
}