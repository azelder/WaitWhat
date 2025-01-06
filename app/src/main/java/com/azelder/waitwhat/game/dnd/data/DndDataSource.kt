package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.dnd.model.DndQuestion
import javax.inject.Inject

private val dndAssetMap = mapOf(
    "balor" to R.drawable.dnd_quiz_balor,
    "basilisk" to R.drawable.dnd_quiz_basilisk,
    "beholder" to R.drawable.dnd_quiz_beholder,
    "bulette" to R.drawable.dnd_quiz_bulette,
    "chimera" to R.drawable.dnd_quiz_chimera,
    "cocatrice" to R.drawable.dnd_quiz_cockatrice,
    "displacer_beast" to R.drawable.dnd_quiz_displacerbeast,
)

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
            guessList + newMonsterToGuess)
    }

    fun startGame() {
        questionSet.clear()
        questionSet.addAll(dndAssetMap.keys)
    }

    fun endGame() {
        questionSet.clear()
        // TODO track results

    }

    fun setQuestionAnswered(question: DndQuestion) : Int {
        questionSet.remove(question.name)
        return questionSet.size
    }
}