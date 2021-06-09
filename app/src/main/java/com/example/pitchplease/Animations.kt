package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Animations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations)
    }

    fun onClickedBack(view: View) {
        finish()
    }

    fun setAnimationsOn(view: View) {
        setAnimations("Animacje włączone")
    }

    fun setAnimationsOff(view: View) {
        setAnimations("Animacje wyłączone")
    }

    private fun setAnimations(lan: String) {
        Toast.makeText(this, lan, Toast.LENGTH_SHORT).show()
    }
}