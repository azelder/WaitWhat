package com.azelder.waitwhat.game

import androidx.lifecycle.ViewModel
import com.azelder.waitwhat.game.data.LeaderboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    val repository: LeaderboardRepository
) : ViewModel() {


}