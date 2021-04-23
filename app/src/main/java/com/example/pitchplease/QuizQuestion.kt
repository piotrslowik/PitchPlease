package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class QuizQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
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