package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.dnd.model.DndQuestion

fun getFakeDndQuestion() = DndQuestion(
    asset = R.drawable.dnd_quiz_aarakocra,
    name = "correct_monster",
    nameList = listOf("correct_monster", "Aboleth", "Beholder", "Bulette")
)
