package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.Continent
import com.azelder.waitwhat.game.model.QuizQuestion

interface QuizRepository {
    /**
     * TODO write docs
     */
    suspend fun getContinents(): List<Continent>
    /**
     * Boolean is to indicate success. This should probably be updated to a sealed game state.
     * @return the number of questions remaining in the game.
     */
    suspend fun startGame(continentCode: String? = null): Int

    /**
     * End the game and reset the data source.
     */
    fun endGame()

    /**
     * Get the next question
     */
    fun getNextQuestion(): QuizQuestion

    /**
     * Set the provided question as answered and return the new game state with new question if needed.
     */
    fun setQuestionAnswered(answer: String): Int
}