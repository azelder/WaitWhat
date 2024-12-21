package com.azelder.waitwhat.game.dnd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azelder.waitwhat.game.dnd.data.DndDataSource
import com.azelder.waitwhat.game.dnd.data.DndQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DndViewModel @Inject constructor(dataSource: DndDataSource) : ViewModel() {

    // here we need the reference to the data source. We will also be storing the current question,
    // and the list of what questions the user has already been asked?

    private val _state = MutableStateFlow(
        dataSource.getQuestion()
    )
    val uiState: StateFlow<DndQuestion> = _state
}