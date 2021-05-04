package com.example.pitchplease

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*

class Sandbox : AppCompatActivity() {
    private var buttons: Array<Button> = arrayOf()
    private var players: Array<MediaPlayer> = arrayOf()
    private var soundsNames: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandbox)
        buttons = getSoundButtons()
        players = Array(12) { _ -> MediaPlayer() }
        soundsNames = resources.getStringArray(R.array.SoundNames)
        setButtons()
        setSpinnerListener()
    }

    private fun setButtons() {
        for ((i, button) in buttons.withIndex()) {
            val chord = getChord(soundsNames[i])
            val soundId: Int = resources.getIdentifier(chord.soundFileName(), "raw", packageName)
            players[i] = MediaPlayer.create(this, soundId)
            button.setOnClickListener {
                handleSound(players[i])
            }
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

    private fun getChord(chordName: String): Chord {
        val scale = findViewById<Spinner>(R.id.spinner_scale_playground).selectedItem.toString();
        return Chord(chordName, scale)
    }

    private fun handleSound(player: MediaPlayer) {
        if (player.isPlaying) {
            player.pause()
            player.seekTo(0)
        }
        player.start()
    }

    private fun setSpinnerListener() {
        findViewById<Spinner>(R.id.spinner_scale_playground).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setButtons()
            }
        }
    }

    fun onClickedBack(view: View) {
        for (player in players) {
            player.release()
        }
        finish()
    }
}