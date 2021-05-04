package com.example.pitchplease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow

class Sandbox : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandbox)
        setButtons()
    }

    private fun setButtons() {
        val buttons = getSoundButtons()
        val soundsNames = resources.getStringArray(R.array.SoundNames)
        findViewById<Button>(R.id.A_playgroud).text = buttons.size.toString()
        for ((i, button) in buttons.withIndex()) {
            button.text = soundsNames[i]
        }
    }

    private fun getSoundButtons(): Array<Button> {
        val buttons = mutableListOf<Button>()
        val container = findViewById<TableLayout>(R.id.soundbuttons_container_sandbox)
        for (i in 0..container.childCount) {
            if (container.getChildAt(i) is TableRow) {
                val row:TableRow = container.getChildAt(i) as TableRow
                for (j in 0..row.childCount) {
                    if (row.getChildAt(j) is Button) {
                        buttons.add(row.getChildAt(j) as Button)
                    }
                }
            }
        }
        return buttons.toTypedArray()
    }

    fun onClickedBack(view: View) {
        finish()
    }
}