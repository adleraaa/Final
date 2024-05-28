package com.example.afinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.afinal.models.Setting

class SettingActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)



        val spinner: Spinner = findViewById(R.id.spinner_setting_activity_category)
        ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val spinnerDifficulty: Spinner = findViewById(R.id.spinner_SetingActivity_Diffculty)
        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty_levels,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDifficulty.adapter = adapter
        }

        val spinnerType: Spinner = findViewById(R.id.spinner_SettingActivity_type)
        ArrayAdapter.createFromResource(
            this,
            R.array.types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerType.adapter = adapter
        }

        findViewById<Button>(R.id.button_SettingActivity_Home).setOnClickListener {
            val selectedCategory = spinner.selectedItem.toString()
            val selectedDifficulty = spinnerDifficulty.selectedItem.toString()
            val selectedType = spinnerType.selectedItem.toString()
            val editTextNumber = findViewById<EditText>(R.id.EditText_SettingActivity_Numbers)
            val input = editTextNumber.inputType

            val setting= Setting (selectedCategory, selectedType, selectedDifficulty,input)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("myObject", setting)
            startActivity(intent)
        }


    }

}