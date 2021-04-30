package com.example.pitchplease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToDifficulty(view: View) {
        val intent = Intent(this, Difficulty::class.java)
        startActivity(intent)
    }

    fun goToSandbox(view: View) {
        val intent = Intent(this, Sandbox::class.java)
        startActivity(intent)
    }

    fun goToRanking(view: View) {
        val intent = Intent(this, Ranking::class.java)
        startActivity(intent)
    }

    fun goToPesonalization(view: View) {
        val intent = Intent(this, Personalization::class.java)
        startActivity(intent)
    }

    fun exitApp(view: View) {
        finishAffinity()
    }
}