package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.model.QuizQuestion
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dataSource: QuizDataSource
) : QuizRepository {
    override suspend fun startGame(): Int {
        return dataSource.startGame()
    }

    override fun endGame() {
        dataSource.endGame()
    }

    override fun getNextQuestion(): QuizQuestion {
        return dataSource.getNextQuestion()
    }

    override fun setQuestionAnswered(answer: String): Int {
        return dataSource.setQuestionAnswered(answer)
    }
}
