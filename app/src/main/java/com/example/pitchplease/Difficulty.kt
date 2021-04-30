package com.example.pitchplease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Difficulty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty)
        setButtonsActions()
    }

    private fun setButtonsActions() {
        val btn1 = findViewById<Button>(R.id.easy_difficulty)
        btn1.setOnClickListener {
            startQuiz(0)
        }
        val btn2 = findViewById<Button>(R.id.medium_difficulty)
        btn2.setOnClickListener {
            startQuiz(1)
        }
        val btn3 = findViewById<Button>(R.id.hard_difficulty)
        btn3.setOnClickListener {
            startQuiz(2)
        }
    }

    private fun startQuiz(difficultyLevel: Int) {
        val intent = Intent(this, QuizQuestion::class.java)
        intent.putExtra("difficulty", difficultyLevel)
        startActivity(intent)
    }

    fun onClickedBack(view: View) {
        finish()
    }
}