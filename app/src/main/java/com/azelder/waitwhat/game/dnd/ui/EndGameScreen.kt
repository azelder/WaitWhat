package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.azelder.waitwhat.R

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

@Preview
@Composable
fun EndGameScreenPreview() {
    EndGameScreen(onNavigateToHome = {})
}