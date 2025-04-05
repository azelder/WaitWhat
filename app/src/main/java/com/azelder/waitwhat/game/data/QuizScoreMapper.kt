package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.db.entity.QuizScoreEntity
import com.azelder.waitwhat.game.data.model.QuizScore
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

// TODO need to confirm that this works
private val formatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.MEDIUM)
    .withZone(ZoneId.systemDefault())

fun QuizScoreEntity.toQuizScore(): QuizScore = QuizScore(
    continentCode = continentCode,
    user = user,
    perfectGuesses = perfectGuesses,
    totalQuestions = totalQuestions,
    formattedTimestamp = formatter.format(timestamp)
)
