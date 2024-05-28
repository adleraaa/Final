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
        var myObject: Setting? = intent.getParcelableExtra("myObject")
        var setting = Setting("Any Category", "Any type", "Any difficulty", 10 )



        findViewById<Button>(R.id.button_MainAcrtivity_Numbers).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_BeginGame).setOnClickListener {
            if(myObject!==null){
                setting = myObject
            }
            startGame()
        }
    }
private fun startGame(){

    startActivity(Intent(this, TrueFalseActivity::class.java))

}
}

