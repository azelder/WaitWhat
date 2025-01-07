package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.game.dnd.model.DndQuestion
import javax.inject.Inject

class DndDataSource @Inject constructor() {

    private val questionSet: MutableSet<String> = mutableSetOf()

    /**
     * @throws NoSuchElementException if there are no more questions in the unasked question set
     */
    @Throws(NoSuchElementException::class)
    fun getNextQuestion(): DndQuestion {
        // get a random monster from the question set that hasn't been asked yet
        val newMonsterToGuess = questionSet.random()
        // get random names from the entire monster name set, combine with the new monster
        val guessList = dndAssetMap.keys.filter {
            it != newMonsterToGuess
        }.shuffled().take(3) + newMonsterToGuess
        return DndQuestion(
            dndAssetMap.getValue(newMonsterToGuess),
            newMonsterToGuess,
            guessList.shuffled()
        )
    }

    fun startGame() {
        questionSet.clear()
        questionSet.addAll(dndAssetMap.keys)
    }

    fun endGame() {
        questionSet.clear()
        // TODO track results
    }

    fun setQuestionAnswered(monsterName: String) : Int {
        questionSet.remove(monsterName)
        return questionSet.size
    }
}