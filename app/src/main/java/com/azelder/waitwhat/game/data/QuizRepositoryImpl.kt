package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.Continent
import com.azelder.waitwhat.game.model.QuizQuestion
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizDataSource: QuizDataSource,
    private val countryDataSource: CountryDataSource
) : QuizRepository {
    override suspend fun getContinents(): List<Continent> {
        return countryDataSource.getContinents()
    }

    override suspend fun startGame(continentCode: String?): Int {
        return quizDataSource.startGame(continentCode)
    }

    override fun endGame() {
        quizDataSource.endGame()
    }

    override fun getNextQuestion(): QuizQuestion {
        return quizDataSource.getNextQuestion()
    }

    override fun setQuestionAnswered(answer: String): Int {
        return quizDataSource.setQuestionAnswered(answer)
    }
}
