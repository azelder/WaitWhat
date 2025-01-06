package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EndGameRoute(
    onNavigateToHome: () -> Unit,
) {
    EndGameScreen(onNavigateToHome)
}

@Composable
fun EndGameScreen(
    onNavigateToHome: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Congratulations! You have finished! How well did you do?"
            )
            // TODO Add artwork to this screen
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToHome
            ) {
                Text(
                    text = "Return to Home"
                )
            }
        }
    }
}