package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion
import javax.inject.Inject

class DndRepositoryImpl @Inject constructor(
    private val dataSource: DndDataSource
) : DndRepository {
    override fun startGame() {
        dataSource.startGame()
    }

    override fun endGame() {
        dataSource.endGame()
    }

    override fun getNextQuestion(): DndQuestion {
        return dataSource.getNextQuestion()
    }

    override fun setQuestionAnswered(monsterName: String) : DndGameState {
        val remaining = dataSource.setQuestionAnswered(monsterName)
        return when {
            remaining <= 0 -> DndGameState.Ended
            else -> DndGameState.InProgress(dataSource.getNextQuestion())
        }
    }

}