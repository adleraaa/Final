package com.example.afinal

import android.content.Intent
import android.media.RouteListingPreference
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.models.Setting
import com.example.afinal.models.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SETTING = 1

    private var setting: Setting = Setting("Any Category","Any type", "Any difficulty", 10)
    private var categoryId: Int? = null
    private var type: String? = null
    private var diffculty: String? = null
    private val amount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myObject: Setting? = intent.getParcelableExtra("myObject")


        findViewById<Button>(R.id.button_MainAcrtivity_Numbers).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_BeginGame).setOnClickListener {
            if(myObject!==null){
                setting = myObject
            }

            startGame()

            val inputStream = resources.openRawResource(R.raw.categoryconvert)
            val jsonString = inputStream.bufferedReader().use {
                it.readText()
            }

            val gson = Gson()
            val type = object : TypeToken<List<Category>>() { }.type
            val categories = gson.fromJson<List<Category>>(jsonString, type)

            categoryId = categories.find { it.name.equals(setting.category, ignoreCase = true) }?.id







        }



    }

    private fun startGame(){
        if(setting.difficulty != "Any difficulty"){
            diffculty = setting.difficulty?.toLowerCase()
        }
        if(setting.type == "Multiple Choice"){
                type = "multiple"
            }
        if(setting.type == "True/False"){
            type = "boolean"
        }


        startActivity(Intent(this, TrueFalseActivity::class.java))

    }

}

