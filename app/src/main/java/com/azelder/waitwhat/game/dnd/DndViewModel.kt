package com.azelder.waitwhat.game.dnd

import androidx.lifecycle.ViewModel
import com.azelder.waitwhat.game.dnd.data.DndGameState
import com.azelder.waitwhat.game.dnd.data.DndRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DndViewModel @Inject constructor(
    private val dndRepository: DndRepository
) : ViewModel() {

    // might want to double check if this should be in a coroutine. or somewhere else?
    // Additionally need to double check there won't be a race condition with startGame and
    // getNextQuestion in _state.
    private val totalQuestions = dndRepository.startGame()
    private var numQuestionsRemaining = totalQuestions

    private val _state: MutableStateFlow<DndGameState> = MutableStateFlow(
        DndGameState.InProgress(dndRepository.getNextQuestion())
    )
    val gameState: StateFlow<DndGameState> = _state.asStateFlow()
    private val _answerResponseState = MutableStateFlow<SnackbarState>(SnackbarState.DoNothing)
    val answerResponseState: StateFlow<SnackbarState> = _answerResponseState.asStateFlow()
    private val _continueButtonState = MutableStateFlow(false)
    val isCurrentQuestionAnsweredState: StateFlow<Boolean> = _continueButtonState.asStateFlow()
    private val _progressState = MutableStateFlow(calculateProgress())
    val progressState = _progressState.asStateFlow()

    fun checkAnswer(choice: String) {
        if (
            gameState.value is DndGameState.InProgress &&
            choice == (gameState.value as DndGameState.InProgress).question.name
        ) {
            _continueButtonState.value = true
            numQuestionsRemaining = dndRepository.setQuestionAnswered(choice)
        } else {
            _answerResponseState.value =
                SnackbarState.Announce("$choice is incorrect! Try again!")
        }
    }

    fun getNextQuestion() {
        if (numQuestionsRemaining == 0) {
            dndRepository.endGame()
            _state.value = DndGameState.Ended
        }
        else {
            _state.value = DndGameState.InProgress(dndRepository.getNextQuestion())
            _continueButtonState.value = false
            _progressState.value = calculateProgress()
        }
    }

    private fun calculateProgress(): Float = (totalQuestions.toFloat() - numQuestionsRemaining.toFloat())/totalQuestions.toFloat()
}

sealed interface SnackbarState {
    data object DoNothing : SnackbarState
    data class Announce(val message: String) : SnackbarState
}