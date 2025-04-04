package com.azelder.waitwhat.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azelder.waitwhat.game.data.LeaderboardRepository
import com.azelder.waitwhat.game.data.QuizGameState
import com.azelder.waitwhat.game.data.QuizRepository
import com.azelder.waitwhat.game.data.model.Continent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val leaderboardRepository: LeaderboardRepository
) : ViewModel() {
    // Note that these aren't state flows because they are closely ties to the continueButtonState
    // so we don't need to worry about them being out of date when recomposition triggers.
    var totalQuestions: Int = -1
        private set
    var numQuestionsRemaining: Int = -1
        private set
    var perfectAnswers: Int = 0
        private set
    var continent: String? = ""

    private val _state: MutableStateFlow<QuizGameState> = MutableStateFlow(
        QuizGameState.NotStarted
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

    init {
        viewModelScope.launch {
            quizRepository.getContinents().apply {
                _state.value = QuizGameState.ChooseContinent(
                    continents = this + Continent(null, "World")
                )
            }
        }
    }

    fun startGame(code: String?) {
        continent = code
        viewModelScope.launch {
            quizRepository.startGame(code).apply {
                totalQuestions = this
                numQuestionsRemaining = this
            }
            getNextQuestion()
        }
    }

    fun checkAnswer(choice: String) {
        if (
            gameState.value is QuizGameState.InProgress &&
            choice == (gameState.value as QuizGameState.InProgress).question.name
        ) {
            _continueButtonState.value = true
            numQuestionsRemaining = quizRepository.setQuestionAnswered(choice)
            // here we want this to record if the user got it right on the first try.
            if (_wrongGuesses.value.isEmpty())
                perfectAnswers++
        } else {
            _answerResponseState.value =
                SnackbarState.Announce("$choice is incorrect. Try again!")
            _wrongGuesses.value += choice
        }
    }

    fun getNextQuestion() {
        _wrongGuesses.value = setOf()
        if (numQuestionsRemaining == 0) {
            quizRepository.endGame()
            _state.value = QuizGameState.Ended
        }
        else {
            _state.value = QuizGameState.InProgress(quizRepository.getNextQuestion())
            _continueButtonState.value = false
            _progressState.value = if (totalQuestions > 0) calculateProgress() else 0f
        }
    }

    private fun calculateProgress(): Float = (totalQuestions.toFloat() - numQuestionsRemaining.toFloat())/totalQuestions.toFloat()

    fun saveScore(name: String) {
        viewModelScope.launch {
            leaderboardRepository.insertScore(
                continent = continent ?: "N/A",
                user = name,
                perfectGuesses = perfectAnswers,
                totalQuestions = totalQuestions,
            )
        }
    }
}

sealed interface SnackbarState {
    data object DoNothing : SnackbarState
    data class Announce(val message: String) : SnackbarState
}