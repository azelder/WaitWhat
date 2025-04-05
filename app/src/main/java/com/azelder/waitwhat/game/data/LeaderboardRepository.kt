package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.QuizScore
import kotlinx.coroutines.flow.Flow

interface LeaderboardRepository {

    suspend fun insertScore(continent: String, user: String?, perfectGuesses: Int, totalQuestions: Int)

    suspend fun getScores(): Flow<List<QuizScore>>
}