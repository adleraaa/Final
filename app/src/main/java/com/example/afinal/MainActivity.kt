package com.example.afinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.models.Question
import com.example.afinal.models.TriviaResponse
import com.example.afinal.models.Setting
class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SETTING = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.textView_MainAcrtivity_Welcome)
        val myObject: Setting? = intent.getParcelableExtra("myObject")

        if(myObject!==null){
            textView.text = myObject.type
        }
        findViewById<Button>(R.id.button_MainAcrtivity_Numbers).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_BeginGame).setOnClickListener {
            startGame()
        }
    }
private fun startGame(){

}
}

