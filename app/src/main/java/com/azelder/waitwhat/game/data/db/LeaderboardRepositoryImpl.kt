package com.azelder.waitwhat.game.data.db

import com.azelder.waitwhat.game.data.LeaderboardRepository
import com.azelder.waitwhat.game.data.db.entity.QuizScoreEntity
import com.azelder.waitwhat.game.data.model.QuizScore
import com.azelder.waitwhat.game.data.toQuizScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LeaderboardRepositoryImpl @Inject constructor(val db: ScoresDB) : LeaderboardRepository {
    override suspend fun insertScore(
        continent: String,
        user: String?,
        perfectGuesses: Int,
        totalQuestions: Int
    ) {
        db.dao.insertScore(
            QuizScoreEntity(
                continentCode = continent,
                user = user,
                perfectGuesses = perfectGuesses,
                totalQuestions = totalQuestions
            )
        )
    }

    override suspend fun getScores(): Flow<List<QuizScore>> =
        db.dao.getAllScores().map { list ->
            list.map { entity ->
                entity.toQuizScore()
            }
        }
}