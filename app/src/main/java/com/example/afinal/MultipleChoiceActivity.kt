package com.example.afinal

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.models.CurrentQuestion
import com.example.afinal.models.FinalResult

class MultipleChoiceActivity : AppCompatActivity() {
    private lateinit var currentQuestion: CurrentQuestion
    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var submitButton: Button
    private var selectedAnswer: String? = null
    private var currentQuestionIndex = 0
    private var score = 0
    private var nextQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        // Initialize views
        questionTextView = findViewById(R.id.textView_MultipleChoiceActivity_Question)
        option1Button = findViewById(R.id.button_MultipleChoiceActivity_Option1)
        option2Button = findViewById(R.id.button_MultipleChoiceActivity_Option2)
        option3Button = findViewById(R.id.button_MultipleChoiceActivity_Option3)
        option4Button = findViewById(R.id.button_MultipleChoiceActivity_Option4)
        submitButton = findViewById(R.id.button_MultipleChoiceActivity_Submit)

        // Get the current question object from the intent
        currentQuestion = intent.getParcelableExtra("status")!!
        currentQuestionIndex = currentQuestion.current
        score = currentQuestion.score
        nextQuestionIndex = currentQuestionIndex + 1

        // Load the first question
        loadQuestion()

        // Set click listeners for the options
        option1Button.setOnClickListener { selectedAnswer = option1Button.text.toString() }
        option2Button.setOnClickListener { selectedAnswer = option2Button.text.toString() }
        option3Button.setOnClickListener { selectedAnswer = option3Button.text.toString() }
        option4Button.setOnClickListener { selectedAnswer = option4Button.text.toString() }

        // Set click listener for the submit button
        submitButton.setOnClickListener {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            } else {
                checkAnswer()
                loadNextQuestion()
            }
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < currentQuestion.result.size) {
            val question = currentQuestion.result[currentQuestionIndex]
            questionTextView.text = Html.fromHtml(question.question, Html.FROM_HTML_MODE_LEGACY)
            val options = question.incorrect_answers.toMutableList()
            options.add(question.correct_answer)
            options.shuffle()

            option1Button.text = Html.fromHtml(options[0], Html.FROM_HTML_MODE_LEGACY)
            option2Button.text = Html.fromHtml(options[1], Html.FROM_HTML_MODE_LEGACY)
            option3Button.text = Html.fromHtml(options[2], Html.FROM_HTML_MODE_LEGACY)
            option4Button.text = Html.fromHtml(options[3], Html.FROM_HTML_MODE_LEGACY)

            Log.d("MultipleChoiceActivity", "Loaded question: ${question.question} at index $currentQuestionIndex")
        } else {
            // No more questions, end the quiz
            endQuiz()
        }
    }

    private fun checkAnswer() {
        val correctAnswer = currentQuestion.result[currentQuestionIndex].correct_answer
        if (selectedAnswer == correctAnswer) {
            score++
            Toast.makeText(this, "Correct Answer! Your score: $score", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong Answer! Your score: $score", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadNextQuestion() {
        nextQuestionIndex = currentQuestionIndex + 1
        Log.d("MultipleChoiceActivity", "Loading next question. Current Index: $currentQuestionIndex, Next Index: $nextQuestionIndex, Total Questions: ${currentQuestion.result.size}")

        if (nextQuestionIndex >= currentQuestion.result.size) {
            endQuiz()
        } else {
            currentQuestionIndex = nextQuestionIndex
            val intent = Intent(this, MultipleChoiceActivity::class.java)
            val nextQuestion = CurrentQuestion(score, currentQuestion.total, currentQuestionIndex, currentQuestion.result)
            intent.putExtra("status", nextQuestion)
            startActivity(intent)
            finish()
        }
    }

    private fun endQuiz() {
        Log.d("MultipleChoiceActivity", "Quiz ended. Final score: $score")
        val finalResult =  FinalResult(score, currentQuestion.total)
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("final result", finalResult)
        startActivity(intent)
        finish()
    }
}