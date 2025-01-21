package com.azelder.waitwhat

import com.azelder.waitwhat.game.dnd.DndViewModel
import com.azelder.waitwhat.game.dnd.SnackbarState
import com.azelder.waitwhat.game.dnd.data.DndGameState
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class DndViewModelTest {

    @Test
    fun checkAnswer_forCorrectAnswer() {
        val viewModelUnderTest = DndViewModel(FakeDndRepository())
        val choice = "correct_monster"

        viewModelUnderTest.checkAnswer(choice)
        assertThat(viewModelUnderTest.isCurrentQuestionAnsweredState.value, `is`(true))
        assertThat(viewModelUnderTest.numQuestionsRemaining, `is`(2))
        assertThat(viewModelUnderTest.answerResponseState.value, `is`(SnackbarState.DoNothing))
    }

    @Test
    fun checkAnswer_forIncorrectAnswer() {
        val viewModelUnderTest = DndViewModel(FakeDndRepository())
        val choice = "incorrect_monster"

        viewModelUnderTest.checkAnswer(choice)
        assertThat(viewModelUnderTest.isCurrentQuestionAnsweredState.value, `is`(false))
        assertThat(viewModelUnderTest.numQuestionsRemaining, `is`(4))
        assertThat(
            viewModelUnderTest.answerResponseState.value,
            `is`(SnackbarState.Announce("incorrect_monster is incorrect. Try again!"))
        )
    }

    @Test
    fun getNextQuestion_endGame() {
        val viewModelUnderTest =
            DndViewModel(FakeDndRepository(startingQuestions = 0, remainingQuestions = 0))
        viewModelUnderTest.getNextQuestion()
        assertThat(viewModelUnderTest.gameState.value, `is`(DndGameState.Ended))
    }

    @Test
    fun getNextQuestion_success() {
        val viewModelUnderTest =
            DndViewModel(FakeDndRepository(startingQuestions = 4, remainingQuestions = 2))
        // For this test, this changes the number of questions remaining to 4 so we don't trigger the end game.
        viewModelUnderTest.checkAnswer("correct_monster")
        viewModelUnderTest.getNextQuestion()
        assertThat(
            viewModelUnderTest.gameState.value, `is`(
                DndGameState.InProgress(
                    getFakeDndQuestion()
                )
            )
        )
        assertThat(viewModelUnderTest.progressState.value, `is`(0.5f))
    }
}