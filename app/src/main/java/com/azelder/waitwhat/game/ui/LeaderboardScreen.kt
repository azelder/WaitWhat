package com.azelder.waitwhat.game.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun LeaderboardRoute(
    onNavigateToHome: () -> Unit,
) {
    LeaderboardScreen(onNavigateToHome)
}

@Composable
fun LeaderboardScreen(
    onNavigateToHome: () -> Unit
) {
    WaitWhatTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        onClick = onNavigateToHome,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        border = ButtonDefaults.outlinedButtonBorder()
                    ) {
                        Text(
                            text = "Return to Home"
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Card(
                    modifier = Modifier
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "Congratulations! You have finished! How well did you do?",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun EndGameScreenPreview() {
    LeaderboardScreen(onNavigateToHome = {})
}