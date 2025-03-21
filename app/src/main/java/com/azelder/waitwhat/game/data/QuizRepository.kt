package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.model.QuizQuestion

interface QuizRepository {
    /**
     * Boolean is to indicate success. This should probably be updated to a sealed game state.
     * @return the number of questions remaining in the game.
     */
    suspend fun startGame(): Int

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

sealed interface QuizGameState {
    data object NotStarted : QuizGameState
    data class InProgress(val question: QuizQuestion) : QuizGameState
    data object Ended : QuizGameState
}