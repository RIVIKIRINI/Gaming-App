package com.example.gamej

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View



class GameView(var c: Context, var gameTask: GameTask) : View(c) {


    private var myPaint: Paint = Paint()
    private var speed = 1
    private var time = 0
    private var score = 0
    private var MainGameCarPosition = 0
    private var otherCars = ArrayList<HashMap<String, Any>>()
    private var highScore = 0

    private var roadOffset = 0f
    private var roadBitmap: Bitmap

    //view
    var viewWidth = 0
    var viewHeight = 0

    init {
        myPaint = Paint()
        loadHighScore()
        roadBitmap = BitmapFactory.decodeResource(resources, R.drawable.road)
    }

    //highscore
    private fun loadHighScore() {
        val sharedPrefs = c.getSharedPreferences("GamePrefs", Context.MODE_PRIVATE)
        highScore = sharedPrefs.getInt("HighScore", 0)
    }

    private fun saveHighScore() {
        val sharedPrefs = c.getSharedPreferences("GamePrefs", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putInt("HighScore", highScore)
            apply()
        }
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight



        if (roadOffset >= viewHeight) {
            roadOffset = 0f
        }
        roadOffset += 20

        canvas.drawBitmap(roadBitmap, null, Rect(0, roadOffset.toInt() - viewHeight, viewWidth, roadOffset.toInt()), myPaint)
        canvas.drawBitmap(roadBitmap, null, Rect(0, roadOffset.toInt(), viewWidth, roadOffset.toInt() + viewHeight), myPaint)

        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherCars.add(map)
            }
            time =time+10+speed

            val carWidth = viewWidth / 5
            val carHeight = carWidth + 6

            myPaint.style = Paint.Style.FILL
            val d = resources.getDrawable(R.drawable.car1, null)

            d.setBounds(
                MainGameCarPosition * viewWidth / 3 + viewWidth / 15 + 25,
                viewHeight -2 - carHeight,
                MainGameCarPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25,
                viewHeight-2
            )
            d.draw(canvas!!)
            myPaint!!.color=Color.GREEN


            for (i in otherCars.indices) {
                try {
                    val carX = otherCars[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                    val carY = time - otherCars[i]["startTime"] as Int
                    val d2 = resources.getDrawable(R.drawable.car2, null)

                    d2.setBounds(
                        carX + 25, carY - carHeight, carX + carWidth - 25, carY
                    )

                    d2.draw(canvas)
                    if (otherCars[i]["lane"] as Int == MainGameCarPosition) {
                        if (carY > viewHeight - 2-carHeight && carY < viewHeight-2) {
                            gameTask.closeGame(score)
                        }
                    }
                    if (carY > viewHeight + carHeight) {
                        otherCars.removeAt(i)
                        score++
                        speed = 1 + Math.abs(score / 4)
                        if (score > highScore) {
                            highScore = score
                            saveHighScore()
                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            myPaint!!.color = Color.WHITE
            myPaint!!.textSize = 40f
            canvas.drawText("Score : $score", 80f, 80f, myPaint!!)
        canvas.drawText("highScore : $highScore", 80f, 120f, myPaint!!)
            canvas.drawText("Speed : $speed", 380f, 80f, myPaint!!)

            invalidate()
        }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {

            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                if (x1 < viewWidth / 2){
                        if (MainGameCarPosition > 0) {
                            MainGameCarPosition--

                        }

                }
                 if (x1 > viewWidth/2) {
                     if (MainGameCarPosition <2) {
                         MainGameCarPosition++

                     }

                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {}
        }
        return true
    }
}