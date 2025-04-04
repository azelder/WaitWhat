package com.azelder.waitwhat.game.ui

import com.azelder.waitwhat.game.data.model.Continent
import com.azelder.waitwhat.game.model.QuizQuestion

sealed interface QuizGameState {
    data object NotStarted : QuizGameState
    data class ChooseContinent(val continents: List<Continent>) : QuizGameState
    data class InProgress(val question: QuizQuestion) : QuizGameState
    data object Ended : QuizGameState
}