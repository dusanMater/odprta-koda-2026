package com.example.am04.model

data class A(
    val response_code: Int,
    val results: List<TriviaQuestion>
)

data class TriviaQuestion(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)
