package com.azelder.waitwhat.game.dnd

import androidx.lifecycle.ViewModel
import com.azelder.waitwhat.game.dnd.data.DndGameState
import com.azelder.waitwhat.game.dnd.data.DndRepository
import com.azelder.waitwhat.game.dnd.model.DndQuestion
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

    private val _state = MutableStateFlow(
        dndRepository.getNextQuestion()
    )
    val uiState: StateFlow<DndQuestion> = _state

    private val _answerResponseState = MutableStateFlow<SnackbarState>(SnackbarState.DoNothing)
    val answerResponseState: StateFlow<SnackbarState> = _answerResponseState.asStateFlow()

    fun checkAnswer(choice: String) {
        if (choice.equals(uiState.value.name)) {
            // go to next question
            _answerResponseState.value = SnackbarState.Announce("That IS a ${choice}! Well done")
            val gameState = dndRepository.setQuestionAnswered(uiState.value)
            when (gameState) {
                is DndGameState.Ended -> {
                    // TODO do something to show game end.
                    // probably launch a new screen...

                }
                else -> _state.value = dndRepository.getNextQuestion()
            }
        } else {
            // emit snackbar
            _answerResponseState.value = SnackbarState.Announce("${choice} is incorrect! Try again!")
        }
    }
}

sealed interface SnackbarState {
    object DoNothing : SnackbarState
    data class Announce(val message: String) : SnackbarState
}