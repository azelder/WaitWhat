package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.game.data.QuizRepository
import com.azelder.waitwhat.game.model.QuizQuestion

class FakeQuizRepository(
    private val startingQuestions: Int = 4,
    private val remainingQuestions: Int = 2
) : QuizRepository {
    override suspend fun startGame(): Int {
        return startingQuestions
    }

    override fun getNextQuestion(): QuizQuestion {
        return getFakeQuizQuestion()
    }

    override fun setQuestionAnswered(answer: String): Int {
        return remainingQuestions
    }

    override fun endGame() {
        // do nothing
    }
}