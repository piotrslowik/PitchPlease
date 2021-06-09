package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
    }

    fun onClickedBack(view: View) {
        finish()
    }

    fun setLanguagePolish(view: View) {
        setLanguage("polski")
    }

    fun setLanguageEnglish(view: View) {
        setLanguage("angielski")
    }

    private fun setLanguage(lan: String) {
        Toast.makeText(this, "Zmieniam język na: $lan", Toast.LENGTH_LONG).show()
    }
}