package com.azelder.waitwhat.game.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.azelder.waitwhat.game.data.db.entity.QuizScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizScoreDao {
    @Insert
    suspend fun insertScore(score: QuizScoreEntity)

    @Query("SELECT * FROM quiz_scores ORDER BY timestamp DESC")
    fun getAllScores(): Flow<List<QuizScoreEntity>>

    @Query("SELECT * FROM quiz_scores WHERE continentCode = :continentCode ORDER BY timestamp DESC")
    fun getScoresForContinent(continentCode: String): Flow<List<QuizScoreEntity>>

    @Query("SELECT * FROM quiz_scores WHERE continentCode IS NULL ORDER BY timestamp DESC")
    fun getWorldScores(): Flow<List<QuizScoreEntity>>

    @Query("SELECT AVG(perfectGuesses * 100.0 / totalQuestions) FROM quiz_scores WHERE continentCode = :continentCode")
    fun getAverageAccuracyForContinent(continentCode: String): Flow<Double?>

    @Query("SELECT AVG(perfectGuesses * 100.0 / totalQuestions) FROM quiz_scores WHERE continentCode IS NULL")
    fun getAverageAccuracyForWorld(): Flow<Double?>
} 