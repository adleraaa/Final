package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.afinal.models.CurrentQuestion
import com.example.afinal.models.FinalResult

class TrueFalseActivity : AppCompatActivity() {
    private lateinit var currentQuestion: CurrentQuestion
    private lateinit var nextQuestion: CurrentQuestion
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private var currentQuestionIndex = 0
    private var score = 0
    private var nextQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_true_false)

        // Initialize views
        questionTextView = findViewById(R.id.textView_TrueFalseActivity_Question)
        trueButton = findViewById(R.id.button_TrueFalseActivity_True)
        falseButton = findViewById(R.id.button_TrueFalseActivity_False)

        // Get the current question object from the intent
        currentQuestion = intent.getParcelableExtra("status")!!
        currentQuestionIndex = currentQuestion.current
        score = currentQuestion.score
        nextQuestionIndex = currentQuestionIndex + 1

        // Load the first question
        loadQuestion()

        // Set click listeners for the buttons
        trueButton.setOnClickListener {
            Log.d("TrueFalseActivity", "True button clicked")
            if (currentQuestion.result[currentQuestionIndex].correct_answer == "True") {
                score++
                Log.d("TrueFalseActivity", "Correct Answer! Score: $score")
                Toast.makeText(this, "Correct Answer! Your score: $score", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TrueFalseActivity", "Wrong Answer! Score: $score")
                Toast.makeText(this, "Wrong Answer! Your score: $score", Toast.LENGTH_SHORT).show()
            }
            loadNextQuestion()
        }

        falseButton.setOnClickListener {
            Log.d("TrueFalseActivity", "False button clicked")
            if (currentQuestion.result[currentQuestionIndex].correct_answer == "False") {
                score++
                Log.d("TrueFalseActivity", "Correct Answer! Score: $score")
                Toast.makeText(this, "Correct Answer! Your score: $score", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TrueFalseActivity", "Wrong Answer! Score: $score")
                Toast.makeText(this, "Wrong Answer! Your score: $score", Toast.LENGTH_SHORT).show()
            }
            loadNextQuestion()
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < currentQuestion.result.size) {
            val question = currentQuestion.result[currentQuestionIndex].question
            questionTextView.text = Html.fromHtml(question, Html.FROM_HTML_MODE_LEGACY)
            Log.d("TrueFalseActivity", "Loaded question: $question at index $currentQuestionIndex")
        } else {
            // No more questions, end the quiz
            endQuiz()
        }
    }

    private fun loadNextQuestion() {
        nextQuestionIndex = currentQuestionIndex + 1
        Log.d("TrueFalseActivity", "Loading next question. Current Index: $currentQuestionIndex, Next Index: $nextQuestionIndex, Total Questions: ${currentQuestion.result.size}")

        if (nextQuestionIndex >= currentQuestion.result.size) {
            endQuiz()
        } else {
            currentQuestionIndex = nextQuestionIndex
            nextQuestion = CurrentQuestion(score, currentQuestion.total, currentQuestionIndex, currentQuestion.result)

            if (currentQuestion.result[nextQuestionIndex].type == "boolean") {
                val intent = Intent(this, TrueFalseActivity::class.java)
                intent.putExtra("status", nextQuestion)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MultipleChoiceActivity::class.java)
                intent.putExtra("status", nextQuestion)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun endQuiz() {
        Log.d("TrueFalseActivity", "Quiz ended. Final score: $score")
        // Optionally, navigate back to the main activity or show a summary screen
        val finalResult =  FinalResult(score, currentQuestion.total)
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("final result", finalResult)
        startActivity(intent)
        finish()
    }
}