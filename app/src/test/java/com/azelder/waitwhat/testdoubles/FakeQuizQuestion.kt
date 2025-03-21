package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.game.model.QuizQuestion

fun getFakeQuizQuestion() = QuizQuestion(
    asset = "ad",
    name = "correct_monster",
    nameList = listOf("correct_monster", "Aboleth", "Beholder", "Bulette")
)
