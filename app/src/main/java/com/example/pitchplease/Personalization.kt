package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Personalization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalization)
    }

    fun onClickedBack(view: View) {
        finish()
    }
}