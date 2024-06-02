package com.example.afinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.api.RetrofitInstance
import com.example.afinal.models.CurrentQuestion
import com.example.afinal.models.Setting
import com.example.afinal.models.Question
import com.example.afinal.models.TriviaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SETTING = 1
    private var setting: Setting = Setting("Any Category", "Any type", "Any difficulty", 10)
    private var categoryId: Int? = null
    private var type: String? = null
    private var difficulty: String? = null
    private var amount = 10
    val categoryMap = mapOf(
        "General Knowledge" to 9,
        "Entertainment: Books" to 10,
        "Entertainment: Film" to 11,
        "Entertainment: Music" to 12,
        "Entertainment: Musicals & Theatres" to 13,
        "Entertainment: Television" to 14,
        "Entertainment: Video Games" to 15,
        "Entertainment: Board Games" to 16,
        "Science & Nature" to 17,
        "Science: Computers" to 18,
        "Science: Mathematics" to 19,
        "Mythology" to 20,
        "Sports" to 21,
        "Geography" to 22,
        "History" to 23,
        "Politics" to 24,
        "Art" to 25,
        "Celebrities" to 26,
        "Animals" to 27,
        "Vehicles" to 28,
        "Entertainment: Comics" to 29,
        "Science: Gadgets" to 30,
        "Entertainment: Japanese Anime & Manga" to 31,
        "Entertainment: Cartoon & Animations" to 32
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myObject: Setting? = intent.getParcelableExtra("myObject")

        findViewById<Button>(R.id.button_MainAcrtivity_Numbers).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.button_MainActivity_BeginGame).setOnClickListener {
            myObject?.let {
                setting = it
            }
            startGame()
        }
    }

    private fun startGame() {
        difficulty = if (setting.difficulty != "Any difficulty") setting.difficulty?.toLowerCase() else null
        type = when (setting.type) {
            "Multiple Choice" -> "multiple"
            "True/False" -> "boolean"
            else -> null
        }
        amount = setting.numbeerOfQuestion
        categoryId = setting.category?.let { getCategoryID(it) }

        fetchQuestions(amount, setting.category, difficulty, type) { questions ->
            questions?.let {
                if (it.isNotEmpty() && it[0].type == "multiple") {
                    val currentQuestion = CurrentQuestion(0, amount, 0, it)
                    val intent = Intent(this, MultipleChoiceActivity::class.java)
                    intent.putExtra("status", currentQuestion)
                    startActivity(intent)
                }
                else{
                    val currentQuestion = CurrentQuestion(0, amount, 0, it)
                    val intent = Intent(this, TrueFalseActivity::class.java)
                    intent.putExtra("status", currentQuestion)
                    startActivity(intent)
                }
            }
        }
    }

    private fun fetchQuestions(amount: Int, categoryName: String?, difficulty: String?, type: String?, callback: (List<Question>?) -> Unit) {
        val categoryID = getCategoryID(categoryName)
        val call = RetrofitInstance.api.getQuestions(amount, categoryID, difficulty, type)
        call.enqueue(object : Callback<TriviaResponse> {
            override fun onResponse(call: Call<TriviaResponse>, response: Response<TriviaResponse>) {
                if (response.isSuccessful) {
                    val questions = response.body()?.results
                    callback(questions)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<TriviaResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    private fun getCategoryID(categoryName: String?): Int? {
        return categoryMap[categoryName]
    }
}