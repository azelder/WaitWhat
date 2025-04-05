package com.azelder.waitwhat.game.data.model

data class QuizScore(
    val continentCode: String?,
    val user: String?,
    val perfectGuesses: Int,
    val totalQuestions: Int,
    val formattedTimestamp: String
)
