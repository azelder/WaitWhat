package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azelder.waitwhat.game.dnd.DndViewModel
import com.azelder.waitwhat.ui.theme.WaitWhatTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azelder.waitwhat.R
import com.azelder.waitwhat.game.dnd.data.DndQuestion

@Composable
fun GameRoute(
    onNavigateBack: () -> Unit,
    viewModel: DndViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun GameScreen(
    uiState: DndQuestion,
    onNavigateBack: () -> Unit
) {
    WaitWhatTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = uiState.asset),
                    contentDescription = "Image of DnD Monster",
                    modifier = Modifier.fillMaxWidth()
                )
                // TODO this needs to be a parameterized list, and not merely duplicated.
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },

                ) {
                    Text(text = uiState.nameList[0])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },

                    ) {
                    Text(text = uiState.nameList[1])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },

                    ) {
                    Text(text = uiState.nameList[2])
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },

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
                    Text(text = "Navigate back")
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
        onNavigateBack = {}
    )
}