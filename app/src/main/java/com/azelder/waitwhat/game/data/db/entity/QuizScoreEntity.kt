package com.azelder.waitwhat.game.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "quiz_scores")
data class QuizScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val continentCode: String?,
    val user: String?,
    val perfectGuesses: Int,
    val totalQuestions: Int,
    val timestamp: Instant = Instant.now()
) 