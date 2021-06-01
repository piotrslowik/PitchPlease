package com.example.pitchplease

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class DBHelper(context: Context) : SQLiteOpenHelper(context, "PitchPleaseDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Results(ID INTEGER PRIMARY KEY AUTOINCREMENT, Difficulty INTEGER, TimeTotal INTEGER, CorrectAnswers INTEGER, Score INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}