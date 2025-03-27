package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.Country
import com.azelder.waitwhat.game.model.QuizQuestion
import javax.inject.Inject

class QuizDataSource @Inject constructor(
    private val countryDataSource: CountryDataSource
) {
    private var countryMap: MutableMap<String, Country> = mutableMapOf()
    private val questionSet: MutableSet<String> = mutableSetOf()

    /**
     * @throws NoSuchElementException if there are no more questions in the unasked question set
     */
    @Throws(NoSuchElementException::class)
    fun getNextQuestion(): QuizQuestion {
        val newQuestionToGuess = questionSet.random()
        val guessList = countryMap.keys.filter {
            it != newQuestionToGuess
        }.shuffled().take(3) + newQuestionToGuess
        return QuizQuestion(
            countryMap[newQuestionToGuess]?.emoji ?: "",
            newQuestionToGuess,
            guessList.shuffled()
        )
    }

    suspend fun startGame(continentCode: String? = null): Int {
        val countries = if (continentCode != null) {
            countryDataSource.getCountriesOnContinent(continentCode)
        } else {
            countryDataSource.getCountries()
        }

        countryMap.putAll(
            countries.map { country ->
                country.name to country
            }
        )
        questionSet.clear()
        questionSet.addAll(countryMap.keys)
        return questionSet.size
    }

    fun endGame() {
        questionSet.clear()
        // TODO track results
    }

    fun setQuestionAnswered(monsterName: String): Int {
        questionSet.remove(monsterName)
        return questionSet.size
    }
}