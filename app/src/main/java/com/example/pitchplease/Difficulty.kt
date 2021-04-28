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
    }

    fun startQuiz(view: View) {
        val btn: Button = view as Button
        val difficulty: String = btn.id.toString()
        val intent = Intent(this, QuizQuestion::class.java)
        intent.apply {
            putExtra(difficulty, difficulty)
        }
        startActivity(intent)
    }

    fun onClickedBack(view: View) {
        super.onBackPressed()
        moveTaskToBack(true);
    }
}