package com.example.pitchplease

import java.util.*

class Chord(name: String, scale: String) {
    private val name = name
    private val scale = scale

    fun soundFileName(): String {
        val chordName = this.name.replace("#", "is")
        val scaleName = this.scale.replace(" ", "")
        val fullName: String =
            if (scaleName == "Dur") chordName.toLowerCase(Locale.ROOT)
            else "${chordName}_${scaleName}".toLowerCase(Locale.ROOT).replace("dur", "")
        val instrument = AppProperties.instrument
        return fullName + "__$instrument";
    }

    fun fullName(): String {
        return if (this.scale == "Dur") this.name
        else "${this.name} ${this.scale}".replace(" Dur", "")
    }
}