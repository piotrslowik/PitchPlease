package com.example.pitchplease

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import raw.Chord
import java.lang.System.currentTimeMillis
import kotlin.random.Random


class QuizQuestion : AppCompatActivity() {
    private var timeStart = currentTimeMillis()
    private var timeTotal: Long = 0
    private var correctAnswers: Int = 0
    private var questionNumber: Int = 1
    private var difficulty: Int = 0
    private var chord: Chord = Chord("", "")
    private var scoreTotal: Long = 0
    private var player: MediaPlayer = MediaPlayer()
    private var questionStarted: Boolean = false
    private var chords = resources.getStringArray(R.array.SoundNames).toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        timeTotal = intent.getIntExtra("timeTotal", 0).toLong()
        correctAnswers = intent.getIntExtra("correctAnswers", 0)
        questionNumber = intent.getIntExtra("questionNumber", 1)
        difficulty = intent.getIntExtra("difficulty", 1)
        scoreTotal = intent.getIntExtra("scoreTotal", 0).toLong()
        chord = getChord()
        player.release()

        setSound()
        setAnswerButtons()
    }

    private fun getChord(): Chord {
        val chordIndex = Random.nextInt(0, chords.size)
        chords.removeAt(chordIndex)
        val chord = chords[chordIndex]
        val scales = getScales()
        val scale = scales[Random.nextInt(0, chords.size)]
        return Chord(chord, scale)
    }

    private fun getScales(): Array<String> {
        return when (difficulty) {
            0 -> arrayOf("moll")
            1 -> arrayOf("dur", "moll")
            else -> arrayOf("dur", "moll", "7", "moll 7")
        }
    }

    private fun setSound() {
        val soundId: Int = resources.getIdentifier(chord.soundFileName(), "raw", null)
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
        val intent: Intent = if (questionNumber == 10)
            Intent(this, QuizFinish::class.java)
        else {
            if (isCorrect)
                Intent(this, AnswerCorrect::class.java)
            else
                Intent(this, AnswerWrong::class.java)
        }
        intent.putExtra("timeTotal", timeTotal)
        intent.putExtra("correctAnswers", correctAnswers + 1)
        intent.putExtra("score", score)
        intent.putExtra("questionNumber", questionNumber++)
        intent.putExtra("difficulty", difficulty)
        intent.putExtra("scoreTotal", scoreTotal)
        startActivity(intent)
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