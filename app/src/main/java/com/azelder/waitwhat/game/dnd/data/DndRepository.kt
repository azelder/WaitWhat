package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion

interface DndRepository {
    /**
     * Boolean is to indicate success. This should probably be updated to a sealed game state.
     */
    fun startGame()

    fun endGame()

    fun getNextQuestion(): DndQuestion

    /**
     * Set the provided question as answered and return the new game state with new question if needed.
     */
    fun setQuestionAnswered(monsterName: String) : DndGameState
}

sealed interface DndGameState {
    data object NotStarted : DndGameState
    data class InProgress(val question: DndQuestion) : DndGameState
    data object Ended : DndGameState
}