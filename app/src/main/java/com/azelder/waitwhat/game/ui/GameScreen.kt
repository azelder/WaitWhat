package com.azelder.waitwhat.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azelder.waitwhat.game.GameViewModel
import com.azelder.waitwhat.game.SnackbarState
import com.azelder.waitwhat.game.data.QuizGameState
import com.azelder.waitwhat.game.data.model.Continent
import com.azelder.waitwhat.game.model.QuizQuestion
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun GameRoute(
    onNavigateBack: () -> Unit,
    onNavigateToEndScreen: () -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val continueButtonState by viewModel.isCurrentQuestionAnsweredState.collectAsStateWithLifecycle()
    val answerMessageState by viewModel.answerResponseState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val progressState by viewModel.progressState.collectAsStateWithLifecycle()
    val wrongGuessState by viewModel.wrongGuessState.collectAsStateWithLifecycle()

    when (gameState) {
        is QuizGameState.Ended -> {
            onNavigateToEndScreen()
        }

        is QuizGameState.ChooseContinent -> {
            ChooseContinent(
                continents = (gameState as QuizGameState.ChooseContinent).continents,
                onContinentSelected = { continentCode ->
                    viewModel.startGame(continentCode)
                }
            )
        }

        is QuizGameState.InProgress -> {
            GameScreen(
                uiState = gameState as QuizGameState.InProgress,
                continueButtonState = continueButtonState,
                progressState = progressState,
                answerMessageState = answerMessageState,
                snackbarHostState = snackbarHostState,
                wrongGuessState = wrongGuessState,
                onNavigateBack = onNavigateBack,
                onCheckAnswer = { viewModel.checkAnswer(it) },
                onContinueToNextQuestion = { viewModel.getNextQuestion() }
            )
        }

        is QuizGameState.NotStarted -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun GameScreen(
    uiState: QuizGameState.InProgress,
    continueButtonState: Boolean,
    progressState: Float,
    answerMessageState: SnackbarState,
    snackbarHostState: SnackbarHostState,
    wrongGuessState: Set<String>,
    onNavigateBack: () -> Unit,
    onCheckAnswer: (String) -> Unit,
    onContinueToNextQuestion: () -> Unit
) {
    WaitWhatTheme {
        LaunchedEffect(answerMessageState) {
            if (answerMessageState is SnackbarState.Announce) {
                snackbarHostState.showSnackbar(answerMessageState.message)
            }
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            bottomBar = {
                BottomButtonWithProgress(
                    continueButtonState,
                    progressState,
                    onContinueToNextQuestion
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = uiState.question.asset,
                    fontSize = 200.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    items(uiState.question.nameList.size) {
                        val name = uiState.question.nameList[it]
                        val isGuessedWrong = wrongGuessState.contains(name)
                        Button(
                            modifier = Modifier
                                .padding(8.dp)
                                .height(100.dp),
                            onClick = { onCheckAnswer(name) },
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                            ),
                            enabled = !isGuessedWrong,
                            border = ButtonDefaults.outlinedButtonBorder()
                        ) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }


            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewGameScreen() {
    GameScreen(
        uiState = QuizGameState.InProgress(
            question = QuizQuestion(
                "\uD83C\uDDE6\uD83C\uDDEA",
                "Sudan",
                listOf("Sudan", "United States", "Democratic Republic of Congo", "China")
            )
        ),
        continueButtonState = true,
        progressState = 0.5f,
        snackbarHostState = SnackbarHostState(),
        answerMessageState = SnackbarState.DoNothing,
        wrongGuessState = setOf(),
        onNavigateBack = {},
        onCheckAnswer = {},
        onContinueToNextQuestion = {}
    )
}

@Preview
@Composable
fun PreviewGameScreenWithJpeg() {
    GameScreen(
        uiState = QuizGameState.InProgress(
            question = QuizQuestion(
                "\uD83C\uDDE6\uD83C\uDDEA",
                "Sudan",
                listOf("Sudan", "United States", "Democratic Republic of Congo", "China")
            )
        ),
        continueButtonState = false,
        progressState = 0.5f,
        snackbarHostState = SnackbarHostState(),
        answerMessageState = SnackbarState.DoNothing,
        wrongGuessState = setOf(),
        onNavigateBack = {},
        onCheckAnswer = {},
        onContinueToNextQuestion = {}
    )
}

@Composable
fun BottomButtonWithProgress(
    continueButtonEnabled: Boolean,
    progressState: Float,
    onContinueToNextQuestion: () -> Unit
) {
    Column {
        LinearProgressIndicator(
            progress = { progressState },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(8.dp),
        )
        BottomAppBar(
            containerColor = Color.Transparent,
        ) {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(64.dp),
                enabled = continueButtonEnabled,
                onClick = { onContinueToNextQuestion() },
                shape = MaterialTheme.shapes.medium,
                border = ButtonDefaults.outlinedButtonBorder(),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    if (continueButtonEnabled)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun ChooseContinent(
    continents: List<Continent>,
    onContinentSelected: (String) -> Unit
) {
    WaitWhatTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Choose a Continent",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    items(continents) { continent ->
                        Button(
                            modifier = Modifier
                                .padding(8.dp)
                                .height(100.dp),
                            onClick = { onContinentSelected(continent.code) },
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                            ),
                            border = ButtonDefaults.outlinedButtonBorder()
                        ) {
                            Text(
                                text = continent.name,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewChooseContinent() {

}