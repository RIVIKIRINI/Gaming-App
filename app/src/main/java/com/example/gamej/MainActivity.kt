package com.example.gamej

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), GameTask {

    lateinit var rootLayout: ConstraintLayout
    lateinit var startBttn: Button
    lateinit var score: TextView
    lateinit var highScoreView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBttn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        highScoreView = findViewById(R.id.highScore)
        updateHighScore()

        startBttn.setOnClickListener {
            startGame()
        }
    }
    override fun closeGame(mScore: Int) {
        score.text = "Score: $mScore"

        updateHighScore()

        rootLayout.removeViewAt(rootLayout.childCount - 1)
        startBttn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        highScoreView.visibility = View.VISIBLE
    }

    private fun updateHighScore() {
        val prefs = getSharedPreferences("GamePrefs", Context.MODE_PRIVATE)
        val highScore = prefs.getInt("HighScore", 0)
        highScoreView.text = "High Score: $highScore"
    }

    private fun startGame() {
        val mGameView = GameView(this, this)
        mGameView.setBackgroundResource(R.drawable.road)
        rootLayout.addView(mGameView)
        startBttn.visibility = View.GONE
        score.visibility = View.GONE
        highScoreView.visibility = View.GONE
    }


}