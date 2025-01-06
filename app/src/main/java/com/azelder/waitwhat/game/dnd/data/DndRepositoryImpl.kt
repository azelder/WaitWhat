package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion
import javax.inject.Inject

class DndRepositoryImpl @Inject constructor(
    private val dataSource: DndDataSource
) : DndRepository {
    override fun startGame() : DndGameState {
        dataSource.startGame()
        return DndGameState.InProgress
    }

    override fun endGame() : DndGameState {
        dataSource.endGame()
        return DndGameState.Ended
    }

    override fun getNextQuestion(): DndQuestion {
        return dataSource.getNextQuestion()
    }

    override fun setQuestionAnswered(question: DndQuestion) : DndGameState {
        val remaining = dataSource.setQuestionAnswered(question)
        return when {
            remaining == 0 -> DndGameState.Ended
            else -> DndGameState.InProgress
        }
    }

}