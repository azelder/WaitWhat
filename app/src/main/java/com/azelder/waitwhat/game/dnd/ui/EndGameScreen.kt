package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azelder.waitwhat.R
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

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
            Image(
                painter = painterResource(id = R.drawable.dnd_bard),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    text = "Congratulations! You have finished! How well did you do?",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun EndGameScreenPreview() {
    EndGameScreen(onNavigateToHome = {})
}