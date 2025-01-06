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

    init {
        // might want to double check if this should be in a coroutine. or somewhere else?
        // Additionally need to double check there won't be a race condition with startGame and
        // getNextQuestion in _state.
        dndRepository.startGame()
    }

    private val _state: MutableStateFlow<DndGameState> = MutableStateFlow(
        DndGameState.InProgress(dndRepository.getNextQuestion())
    )
    val uiState: StateFlow<DndGameState> = _state.asStateFlow()

    private val _answerResponseState = MutableStateFlow<SnackbarState>(SnackbarState.DoNothing)
    val answerResponseState: StateFlow<SnackbarState> = _answerResponseState.asStateFlow()

    fun checkAnswer(choice: String) {
        // TODO might want to store the current question in a variable rather than a state that might not have a question...
        if (uiState.value is DndGameState.InProgress && choice.equals((uiState.value as DndGameState.InProgress).question.name)) {
            _answerResponseState.value = SnackbarState.Announce("That IS a ${choice}! Well done")
            // go to next question
            _state.value = dndRepository.setQuestionAnswered(choice)
        } else {
            _answerResponseState.value = SnackbarState.Announce("${choice} is incorrect! Try again!")
        }
    }
}

sealed interface SnackbarState {
    data object DoNothing : SnackbarState
    data class Announce(val message: String) : SnackbarState
}