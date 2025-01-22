package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.model.QuizQuestion

fun getFakeQuizQuestion() = QuizQuestion(
    asset = R.drawable.dnd_quiz_aarakocra,
    name = "correct_monster",
    nameList = listOf("correct_monster", "Aboleth", "Beholder", "Bulette")
)
