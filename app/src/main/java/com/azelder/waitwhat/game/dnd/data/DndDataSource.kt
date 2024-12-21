package com.azelder.waitwhat.game.dnd.data

import com.azelder.waitwhat.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

data class DndQuestion(val asset: Int, val name: String, val nameList: List<String>)

class DndDataSource @Inject constructor() {
    // To start, we'll return a single asset, along with 3 other random names.
    fun getQuestion(): DndQuestion {
        val nameList = dndAssetMap.keys.shuffled().take(4)
        val assetName = nameList.random()
        val asset = dndAssetMap.getValue(assetName)
        return DndQuestion(asset, assetName, nameList + assetName)
    }

    fun getQuestionFlow(): Flow<DndQuestion> = flow {
        emit(getQuestion())
    }

    fun getAssetNames() = dndAssetMap.keys.toList()

}