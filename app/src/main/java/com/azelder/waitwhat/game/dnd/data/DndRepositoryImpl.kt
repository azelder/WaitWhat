package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion
import javax.inject.Inject

class DndRepositoryImpl @Inject constructor(
    private val dataSource: DndDataSource
) : DndRepository {
    override fun startGame(): Int {
        return dataSource.startGame()
    }

    override fun endGame() {
        dataSource.endGame()
    }

    override fun getNextQuestion(): DndQuestion {
        return dataSource.getNextQuestion()
    }

    override fun setQuestionAnswered(monsterName: String): Int {
        return dataSource.setQuestionAnswered(monsterName)
    }
}
