package com.azelder.waitwhat.game

import androidx.lifecycle.ViewModel
import com.azelder.waitwhat.game.data.QuizGameState
import com.azelder.waitwhat.game.data.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val totalQuestions = quizRepository.startGame()
    var numQuestionsRemaining = totalQuestions
        private set

    private val _state: MutableStateFlow<QuizGameState> = MutableStateFlow(
        QuizGameState.InProgress(quizRepository.getNextQuestion())
    )
    val gameState: StateFlow<QuizGameState> = _state.asStateFlow()
    private val _answerResponseState = MutableStateFlow<SnackbarState>(SnackbarState.DoNothing)
    val answerResponseState: StateFlow<SnackbarState> = _answerResponseState.asStateFlow()
    private val _continueButtonState = MutableStateFlow(false)
    val isCurrentQuestionAnsweredState: StateFlow<Boolean> = _continueButtonState.asStateFlow()
    private val _progressState = MutableStateFlow(calculateProgress())
    val progressState = _progressState.asStateFlow()

    private var _wrongGuesses = MutableStateFlow(setOf<String>())
    val wrongGuessState: StateFlow<Set<String>> = _wrongGuesses.asStateFlow()


    fun checkAnswer(choice: String) {
        if (
            gameState.value is QuizGameState.InProgress &&
            choice == (gameState.value as QuizGameState.InProgress).question.name
        ) {
            _continueButtonState.value = true
            numQuestionsRemaining = quizRepository.setQuestionAnswered(choice)
        } else {
            _answerResponseState.value =
                SnackbarState.Announce("$choice is incorrect. Try again!")
            _wrongGuesses.value += choice
        }
    }

    fun getNextQuestion() {
        if (numQuestionsRemaining == 0) {
            quizRepository.endGame()
            _state.value = QuizGameState.Ended
            _wrongGuesses.value = setOf()
        }
        else {
            _state.value = QuizGameState.InProgress(quizRepository.getNextQuestion())
            _continueButtonState.value = false
            _progressState.value = if (totalQuestions > 0) calculateProgress() else 0f
            _wrongGuesses.value = setOf()
        }
    }

    private fun calculateProgress(): Float = (totalQuestions.toFloat() - numQuestionsRemaining.toFloat())/totalQuestions.toFloat()
}

sealed interface SnackbarState {
    data object DoNothing : SnackbarState
    data class Announce(val message: String) : SnackbarState
}