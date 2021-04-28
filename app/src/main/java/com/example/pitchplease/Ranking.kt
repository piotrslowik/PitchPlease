package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
    }

    fun onClickedBack(view: View) {
        super.onBackPressed()
        moveTaskToBack(true);
    }
}