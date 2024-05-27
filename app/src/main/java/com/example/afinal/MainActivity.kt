package com.example.afinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.models.TriviaResponse
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_MainActivity_Diffculty).setOnClickListener {
            startActivity(Intent(this, DiffcultyActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_Cagagory).setOnClickListener {
            startActivity(Intent(this, CatagoryActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainAcrtivity_Numbers).setOnClickListener {
            startActivity(Intent(this, NumberOfQuestionsActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_BeginGame).setOnClickListener {
            startGame()
        }


    }
private fun startGame(){

}
}