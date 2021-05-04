package com.example.pitchplease

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.System.currentTimeMillis
import kotlin.random.Random


class QuizQuestion : AppCompatActivity() {
    private var timeStart = currentTimeMillis()
    private var timeTotal: Long = 0
    private var correctAnswers: Int = 0
    private var questionNumber: Int = 0
    private var difficulty: Int = 0
    private var chord: Chord = Chord("", "")
    private var scoreTotal: Long = 0
    private var player: MediaPlayer = MediaPlayer()
    private var questionStarted: Boolean = false
    private var chords = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chords = resources.getStringArray(R.array.SoundNames).toMutableList()
        timeTotal = intent.getLongExtra("timeTotal", 0)
        correctAnswers = intent.getIntExtra("correctAnswers", 0)
        questionNumber = intent.getIntExtra("questionNumber", 0)
        difficulty = intent.getIntExtra("difficulty", 0)
        scoreTotal = intent.getLongExtra("scoreTotal", 0)
        chord = getChord()
        player.release()

        setContentView(R.layout.activity_quiz_question)
        setSound()
        setAnswerButtons()
    }

    private fun getChord(): Chord {
        val chordIndex = Random.nextInt(0, chords.size)
        val chord = chords[chordIndex]
        chords.removeAt(chordIndex)
        val scales = getScales()
        val scale = scales[Random.nextInt(0, scales.size)]
        return Chord(chord, scale)
    }

    private fun getScales(): Array<String> {
        val scales: Array<String> = resources.getStringArray(R.array.ScaleNames)
        return when (difficulty) {
            0 -> arrayOf(scales[1])
            1 -> arrayOf(scales[0], scales[1])
            else -> scales
        }
    }

    private fun setSound() {
        val soundId: Int = resources.getIdentifier(chord.soundFileName(), "raw", packageName)
        player = MediaPlayer.create(this, soundId)
    }

    fun onQuestionClicked(view: View) {
        player.start()
        if (!questionStarted) {
            questionStarted = true
            timeStart = currentTimeMillis()
        }
    }

    private fun onCorrectAnswer() {
        val time: Long = currentTimeMillis() - timeStart
        timeTotal += time

        var score = 500 - (time / 20)
        if (score < 0) score = 0
        score += 100
        scoreTotal += score

        nextActivity(timeTotal, correctAnswers + 1, score, true)
    }

    private fun onWrongAnswer() {
        val time: Long = currentTimeMillis() - timeStart
        timeTotal += time

        nextActivity(timeTotal, correctAnswers, 0, false)
    }

    private fun nextActivity(timeTotal: Long, correctAnswers: Int, score: Long, isCorrect: Boolean) {
        player.release()
        val intent: Intent = if (isCorrect)
            Intent(this, AnswerCorrect::class.java)
        else
            Intent(this, AnswerWrong::class.java)
        intent.putExtra("timeTotal", timeTotal)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("score", score)
        intent.putExtra("questionNumber", questionNumber + 1)
        intent.putExtra("difficulty", difficulty)
        intent.putExtra("scoreTotal", scoreTotal)
        intent.putExtra("chord", chord.fullName())
        startActivity(intent)
        finish()
    }

    private fun setAnswerButtons() {
        val buttons: Array<Button> = arrayOf(
            findViewById<Button>(R.id.answer_button_quiz_question_1),
            findViewById<Button>(R.id.answer_button_quiz_question_2),
            findViewById<Button>(R.id.answer_button_quiz_question_3),
            findViewById<Button>(R.id.answer_button_quiz_question_4)
        )
        val correctAnswerIndex: Int = Random.nextInt(0, 4);
        val wrongChords: Array<Chord> = arrayOf(getChord(), getChord(), getChord())
        var wrongI: Int = 0

        for (i in 0..3) {
            if (i == correctAnswerIndex) {
                buttons[i].text = chord.fullName()
                buttons[i].setOnClickListener {
                    onCorrectAnswer()
                }
            }
            else {
                buttons[i].text = wrongChords[wrongI].fullName()
                buttons[i].setOnClickListener {
                    onWrongAnswer()
                }
                wrongI++
            }
        }
    }
}