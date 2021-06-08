package com.example.pitchplease

class RankingItem(answers: Int, time: Int, score: Int) {
    val correctAnswers = answers
    private val timeTotal = time
    val scoreTotal = score

    fun time(): String {
        val t: Float = timeTotal.toFloat() / 1000
        return "%.2f s".format(t)
    }
}