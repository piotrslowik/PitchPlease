package com.example.pitchplease

import android.database.Cursor
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        setSpinnerEvents()
    }

    private fun setSpinnerEvents() {
        val spinner = findViewById<View>(R.id.spinner_level_ranking) as Spinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, id: Long) {
                loadFromDB(position)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }

    private fun loadFromDB(difficulty: Int) {
        val recycler: RecyclerView = findViewById(R.id.recycler_ranking)
        recycler.layoutManager = LinearLayoutManager(this@Ranking)
        recycler.adapter = RecyclerAdapter()
        val db = DBHelper(applicationContext).readableDatabase
        val selectQuery = "SELECT * FROM Results WHERE Difficulty=$difficulty ORDER BY Score DESC"
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
            val items = retrieveItems(cursor)
            (recycler.adapter as RecyclerAdapter).setItems(items)
        }
        catch (e: SQLiteException) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
        finally {
            cursor?.close()
        }
    }

    private fun retrieveItems(cursor: Cursor): MutableList<RankingItem> {
        val items: MutableList<RankingItem> = mutableListOf()
        var timeTotal: Int
        var correctAnswers: Int
        var scoreTotal: Int
        if (cursor.moveToFirst()) {
            do {
                timeTotal = cursor.getInt(cursor.getColumnIndex("TimeTotal"))
                correctAnswers = cursor.getInt(cursor.getColumnIndex("CorrectAnswers"))
                scoreTotal = cursor.getInt(cursor.getColumnIndex("Score"))
                val item = RankingItem(correctAnswers, timeTotal, scoreTotal)
                items.add(item)
            } while (cursor.moveToNext())
        }
        return items
    }

    fun onClickedBack(view: View) {
        finish()
    }
}

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<RankingItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_ranking_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListItemHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun setItems(items: MutableList<RankingItem>) {
        this.items = items
    }

    class ListItemHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val answers: TextView = itemView.findViewById(R.id.ranking_item_answers)
        private val score: TextView = itemView.findViewById(R.id.ranking_item_score)
        private val time: TextView = itemView.findViewById(R.id.ranking_item_time)

        fun bind(item: RankingItem){
            answers.text = "${item.correctAnswers}/10"
            score.text = item.scoreTotal.toString()
            time.text = item.time()
        }
    }

//    class ListItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        private lateinit var answers: TextView
//        private lateinit var score: TextView
//        private lateinit var time: TextView
//
//        init {
//            answers = itemView.findViewById(R.id.ranking_item_answers)
//            score = itemView.findViewById(R.id.ranking_item_score)
//            time = itemView.findViewById(R.id.ranking_item_time)
//        }
//
//        fun bind(item: RankingItem){
//            answers.text = "${item.correctAnswers}/10"
//            score.text = item.scoreTotal.toString()
//            time.text = item.time()
//        }
//    }
}