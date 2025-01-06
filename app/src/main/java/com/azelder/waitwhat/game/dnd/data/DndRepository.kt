package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion

interface DndRepository {
    /**
     * Boolean is to indicate success. This should probably be updated to a sealed game state.
     */
    fun startGame(): DndGameState

    fun endGame(): DndGameState

    fun getNextQuestion(): DndQuestion

    fun setQuestionAnswered(question: DndQuestion) : DndGameState
}

sealed interface DndGameState {
    data object NotStarted : DndGameState
    data object InProgress : DndGameState
    data object Ended : DndGameState
}