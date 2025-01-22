package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.model.QuizQuestion
import javax.inject.Inject

class QuizDataSource @Inject constructor() {

    private val questionSet: MutableSet<String> = mutableSetOf()

    /**
     * @throws NoSuchElementException if there are no more questions in the unasked question set
     */
    @Throws(NoSuchElementException::class)
    fun getNextQuestion(): QuizQuestion {
        // get a random monster from the question set that hasn't been asked yet
        val newQuestionToGuess = questionSet.random()
        // get random names from the entire monster name set, combine with the new monster
        val guessList = flagAssetMap.keys.filter {
            it != newQuestionToGuess
        }.shuffled().take(3) + newQuestionToGuess
        return QuizQuestion(
            flagAssetMap.getValue(newQuestionToGuess),
            newQuestionToGuess,
            guessList.shuffled()
        )
    }

    fun startGame() : Int {
        questionSet.clear()
        questionSet.addAll(flagAssetMap.keys)
        return questionSet.size
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