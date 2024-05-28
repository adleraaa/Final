package com.example.afinal.models


data class TriviaResponse(
    val response_code: Int,
    val results: List<Question>
)