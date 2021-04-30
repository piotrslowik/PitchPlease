package com.example.pitchplease

class Chord(name: String, scale: String) {
    private val name = name
    private val scale = scale

    fun soundFileName(): String {
        val chordName = this.name.replace("#", "is")
        val scaleName = this.scale.replace(" ", "")
        return if (scaleName == "dur") chordName.toLowerCase()
        else "${chordName}_${scaleName}".toLowerCase()
    }

    fun fullName(): String {
        return if (this.scale == "dur") this.name
        else "${this.name} ${this.scale}"
    }
}