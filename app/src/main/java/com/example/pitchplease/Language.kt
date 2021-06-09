package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import layout.AppProperties

class Language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
    }

    fun onClickedBack(view: View) {
        finish()
    }

    fun setLanguagePolish(view: View) {
        AppProperties.lang = "pl"
        setLanguage("polski")
    }

    fun setLanguageEnglish(view: View) {
        AppProperties.lang = "en"
        setLanguage("angielski")
    }

    private fun setLanguage(lan: String) {
        Toast.makeText(this, "Zmieniam język na: $lan", Toast.LENGTH_LONG).show()
    }
}