package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Sandbox : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandbox)
    }

    fun onClickedBack(view: View) {
        finish()
    }
}