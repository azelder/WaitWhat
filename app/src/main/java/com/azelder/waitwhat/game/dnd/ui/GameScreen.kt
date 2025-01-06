package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.dnd.DndViewModel
import com.azelder.waitwhat.game.dnd.SnackbarState
import com.azelder.waitwhat.game.dnd.model.DndQuestion
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun GameRoute(
    onNavigateBack: () -> Unit,
    viewModel: DndViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val answerMessageState by viewModel.answerResponseState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    GameScreen(
        uiState = uiState,
        answerMessageState = answerMessageState,
        snackbarHostState = snackbarHostState,
        onNavigateBack = onNavigateBack,
        onCheckAnswer = { viewModel.checkAnswer(it) }
    )
}

@Composable
fun GameScreen(
    uiState: DndQuestion,
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
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Image(
                    painter = painterResource(id = uiState.asset),
                    contentDescription = "Image of DnD Monster",
                    modifier = Modifier.fillMaxWidth()
                )
                // TODO this should be a parameterized list, and not merely duplicated.
                // TODO, perhaps this could be a grid actually?
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCheckAnswer(uiState.nameList[0]) },

                ) {
                    Text(text = uiState.nameList[0])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCheckAnswer(uiState.nameList[1]) },

                    ) {
                    Text(text = uiState.nameList[1])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCheckAnswer(uiState.nameList[2]) },

                    ) {
                    Text(text = uiState.nameList[2])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCheckAnswer(uiState.nameList[3]) },

                    ) {
                    Text(text = uiState.nameList[3])
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onNavigateBack() },
                ) {
                    Text(text = "End game")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen(
        uiState = DndQuestion(R.drawable.dnd_quiz_balor, "Balor", listOf("Balor", "Basilisk", "Beholder", "Cockatrice")),
        snackbarHostState = SnackbarHostState(),
        answerMessageState = SnackbarState.DoNothing,
        onNavigateBack = {},
        onCheckAnswer = {}
    )
}