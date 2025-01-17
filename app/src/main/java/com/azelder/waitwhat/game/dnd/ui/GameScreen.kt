package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.dnd.DndViewModel
import com.azelder.waitwhat.game.dnd.SnackbarState
import com.azelder.waitwhat.game.dnd.data.DndGameState
import com.azelder.waitwhat.game.dnd.model.DndQuestion
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun GameRoute(
    onNavigateBack: () -> Unit,
    onNavigateToEndScreen: () -> Unit,
    viewModel: DndViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val answerMessageState by viewModel.answerResponseState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    when (uiState) {
        is DndGameState.Ended -> {
            onNavigateToEndScreen()
        }
        is DndGameState.InProgress -> {
            GameScreen(
                uiState = uiState as DndGameState.InProgress,
                answerMessageState = answerMessageState,
                snackbarHostState = snackbarHostState,
                onNavigateBack = onNavigateBack,
                onCheckAnswer = { viewModel.checkAnswer(it) }
            )
        }
        is DndGameState.NotStarted -> {
            // this might not be necessary?
        }
    }
}

@Composable
fun GameScreen(
    uiState: DndGameState.InProgress,
    answerMessageState: SnackbarState,
    snackbarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
    onCheckAnswer: (String) -> Unit
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
                BottomAppBar(
                    containerColor = Color.Transparent,
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth().height(64.dp),
                        onClick = { onNavigateBack() },
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Text(text = "Continue")
                    }
                }
            }
        ) { innerPadding ->
            Image(
                painter = painterResource(id = R.drawable.dnd_parchment),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Image(
                    painter = painterResource(id = uiState.question.asset),
                    contentDescription = "Image of DnD Monster",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentScale = ContentScale.FillWidth
                )
                // TODO this should be a parameterized list, and not merely duplicated.
                // TODO, perhaps this could be a grid actually?
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    items(uiState.question.nameList.size) {
                        val name = uiState.question.nameList[it]
                        Button(
                            modifier = Modifier.padding(8.dp).height(124.dp),
                            onClick = { onCheckAnswer(name) },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(text = name)
                        }
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen(
        uiState = DndGameState.InProgress(
            question = DndQuestion(
                R.drawable.dnd_quiz_balor, "Balor", listOf("Balor", "Basilisk", "Beholder", "Cockatrice")
            )
        ),
        snackbarHostState = SnackbarHostState(),
        answerMessageState = SnackbarState.DoNothing,
        onNavigateBack = {},
        onCheckAnswer = {}
    )
}

@Preview
@Composable
fun PreviewGameScreenWithJpeg() {
    GameScreen(
        uiState = DndGameState.InProgress(
            question = DndQuestion(
                R.drawable.dnd_quiz_badger, "Balor", listOf("Balor", "Basilisk", "Beholder", "Cockatrice")
            )
        ),
        snackbarHostState = SnackbarHostState(),
        answerMessageState = SnackbarState.DoNothing,
        onNavigateBack = {},
        onCheckAnswer = {}
    )
}