package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Instrument : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrument)
    }

    fun onClickedBack(view: View) {
        finish()
    }

    fun setInstrumentGuitar(view: View) {
        AppProperties.instrument = "g"
        setInstrument("Gitara")
    }

    fun setInstrumentPiano(view: View) {
        AppProperties.instrument = "p"
        setInstrument("Pianino")
    }

    private fun setInstrument(lan: String) {
        Toast.makeText(this, lan, Toast.LENGTH_SHORT).show()
    }
}