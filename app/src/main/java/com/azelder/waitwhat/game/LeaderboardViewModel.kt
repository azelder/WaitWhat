package com.azelder.waitwhat.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azelder.waitwhat.game.data.LeaderboardRepository
import com.azelder.waitwhat.game.data.model.QuizScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repository: LeaderboardRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LeaderboardState> = MutableStateFlow(LeaderboardState.Loading)
    val uiState: StateFlow<LeaderboardState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getScores().collectLatest { list ->
                _uiState.value = LeaderboardState.ShowLeaderboard(list)
            }
        }
    }
}

sealed interface LeaderboardState {
    data object Loading: LeaderboardState
    data class ShowLeaderboard(val scores: List<QuizScore>): LeaderboardState
}