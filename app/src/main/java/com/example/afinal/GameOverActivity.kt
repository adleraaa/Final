package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.afinal.models.FinalResult

class GameOverActivity : AppCompatActivity() {

    private lateinit var finalresult: FinalResult
    private lateinit var result: TextView
    private lateinit var home: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        result = findViewById(R.id.textView_GameOverActivity_Result)
        home = findViewById(R.id.button_GameOverActivity_home)

        finalresult = intent.getParcelableExtra("final result")!!
        val finalResultText = "You get " + finalresult.totalQuestion + " out of " + finalresult.score + " congrelation!"
        result.setText(finalResultText)

        home.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}