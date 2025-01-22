package com.azelder.waitwhat.testdoubles

import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.model.QuizQuestion

fun getFakeQuizQuestion() = QuizQuestion(
    asset = R.drawable.flag_ad,
    name = "correct_monster",
    nameList = listOf("correct_monster", "Aboleth", "Beholder", "Bulette")
)
