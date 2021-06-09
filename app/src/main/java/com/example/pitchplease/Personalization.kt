package com.example.pitchplease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Personalization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalization)
    }

    fun goToLanguage(view: View) {
        val intent = Intent(this, Language::class.java)
        startActivity(intent)
    }

    fun goToInstrument(view: View) {
        val intent = Intent(this, Instrument::class.java)
        startActivity(intent)
    }

    fun goToAnimations(view: View) {
        val intent = Intent(this, Animations::class.java)
        startActivity(intent)
    }

    fun onClickedBack(view: View) {
        finish()
    }
}