package com.azelder.waitwhat.game.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azelder.waitwhat.game.LeaderboardState
import com.azelder.waitwhat.game.LeaderboardViewModel
import com.azelder.waitwhat.game.data.model.QuizScore
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun LeaderboardRoute(
    onNavigateToHome: () -> Unit,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    when (val screenState = state.value) {
        is LeaderboardState.Loading -> {

        }
        is LeaderboardState.ShowLeaderboard -> {
            LeaderboardScreen(onNavigateToHome, screenState.scores)
        }
    }
}

@Composable
fun LeaderboardScreen(
    onNavigateToHome: () -> Unit,
    scores: List<QuizScore>
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
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "Leaderboard",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }
                
                items(scores) { score ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = score.user ?: "Anonymous",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            
                            Text(
                                text = "${score.perfectGuesses} / ${score.totalQuestions} perfect guesses",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            
                            Text(
                                text = score.continentCode ?: "All Continents",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            
                            Text(
                                text = score.formattedTimestamp,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.padding(top = 4.dp)
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
fun EndGameScreenPreview() {
    val scores = listOf(
        QuizScore("Africa", "Azelder", 5, 10, "2023-10-01T12:00:00Z"),
        QuizScore("Europe", "John", 7, 10, "2023-10-01T12:00:00Z"),
        QuizScore("Asia", null, 8, 10, "2023-10-01T12:00:00Z")
    )
    LeaderboardScreen(onNavigateToHome = {}, scores)
}