package com.example.pitchplease

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class QuizFinish : AppCompatActivity() {
    private var timeTotal: Long = 0
    private var correctAnswers: Int = 0
    private var difficulty: Int = 0
    private var scoreTotal: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_finish)
        timeTotal = intent.getLongExtra("timeTotal", 0)
        correctAnswers = intent.getIntExtra("correctAnswers", 0)
        difficulty = intent.getIntExtra("difficulty", 1)
        scoreTotal = intent.getLongExtra("scoreTotal", 0)
        setResultView()
        setScoreView()
        saveToDB()
    }

    private fun setResultView() {
        val box: TextView = findViewById<TextView>(R.id.result_quiz_finish)
        box.text = "WYNIK: ${correctAnswers}/10"
    }

    private fun setScoreView() {
        val box: TextView = findViewById<TextView>(R.id.points_quiz_finish)
        box.text = "${scoreTotal} PUNKTÓW"
    }

    private fun saveToDB() {
        val db = DBHelper(applicationContext).readableDatabase
        val cv = ContentValues()
        cv.put("Difficulty", difficulty)
        cv.put("TimeTotal", timeTotal)
        cv.put("CorrectAnswers", correctAnswers)
        cv.put("Score", scoreTotal)
        db.insert("Results", null, cv)
    }

    fun startQuiz(view: View) {
        val intent = Intent(this, QuizQuestion::class.java)
        intent.putExtra("difficulty", difficulty)
        startActivity(intent)
    }

    fun goToMainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}