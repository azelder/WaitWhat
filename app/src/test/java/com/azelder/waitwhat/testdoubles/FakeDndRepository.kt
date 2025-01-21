package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.game.dnd.data.DndRepository
import com.azelder.waitwhat.game.dnd.model.DndQuestion

class FakeDndRepository(
    private val startingQuestions: Int = 4,
    private val remainingQuestions: Int = 2
) : DndRepository {
    override fun startGame(): Int {
        return startingQuestions
    }

    override fun getNextQuestion(): DndQuestion {
        return getFakeDndQuestion()
    }

    override fun setQuestionAnswered(monsterName: String): Int {
        return remainingQuestions
    }

    override fun endGame() {
        // do nothing
    }
}