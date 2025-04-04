package com.azelder.waitwhat

import com.azelder.waitwhat.game.GameViewModel
import com.azelder.waitwhat.game.SnackbarState
import com.azelder.waitwhat.game.ui.QuizGameState
import com.azelder.waitwhat.testdoubles.FakeQuizRepository
import com.azelder.waitwhat.testdoubles.getFakeQuizQuestion
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GameViewModelTest {

    @Test
    fun checkAnswer_forCorrectAnswer() {
        val viewModelUnderTest = GameViewModel(FakeQuizRepository())
        val choice = "correct_monster"

        viewModelUnderTest.checkAnswer(choice)
        assertThat(viewModelUnderTest.isCurrentQuestionAnsweredState.value, `is`(true))
        assertThat(viewModelUnderTest.numQuestionsRemaining, `is`(2))
        assertThat(viewModelUnderTest.answerResponseState.value, `is`(SnackbarState.DoNothing))
    }

    @Test
    fun checkAnswer_forIncorrectAnswer() {
        val viewModelUnderTest = GameViewModel(FakeQuizRepository())
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
            GameViewModel(FakeQuizRepository(startingQuestions = 0, remainingQuestions = 0))
        viewModelUnderTest.getNextQuestion()
        assertThat(viewModelUnderTest.gameState.value, `is`(QuizGameState.Ended))
    }

    @Test
    fun getNextQuestion_success() {
        val viewModelUnderTest =
            GameViewModel(FakeQuizRepository(startingQuestions = 4, remainingQuestions = 2))
        // For this test, this changes the number of questions remaining to 4 so we don't trigger the end game.
        viewModelUnderTest.checkAnswer("correct_monster")
        viewModelUnderTest.getNextQuestion()
        assertThat(
            viewModelUnderTest.gameState.value, `is`(
                QuizGameState.InProgress(
                    getFakeQuizQuestion()
                )
            )
        )
        assertThat(viewModelUnderTest.progressState.value, `is`(0.5f))
    }
}