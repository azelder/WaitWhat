package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
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
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onNavigateBack() },
                ) {
                    Text(text = "Navigate back")
                }
                Text(text = "Purely for show")
            }
        }
    }
}