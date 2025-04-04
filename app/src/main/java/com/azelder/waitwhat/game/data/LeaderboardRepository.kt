package com.azelder.waitwhat.game.data

interface LeaderboardRepository {

    suspend fun insertScore(continent: String, user: String?, perfectGuesses: Int, totalQuestions: Int)
}