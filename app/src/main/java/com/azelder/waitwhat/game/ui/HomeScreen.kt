package com.azelder.waitwhat.game.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azelder.waitwhat.R
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun HomeScreen(onNavigateToGameScreen: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition(label = "world_map_animation")
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -150f,
        targetValue = 150f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 35000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "world_map_offset"
    )

    WaitWhatTheme {
        Scaffold (
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color.Transparent),
                        onClick = { onNavigateToGameScreen() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        border = ButtonDefaults.outlinedButtonBorder()
                    ) {
                        Text(text = "Lets play!")
                    }
                }
            }) { innerPadding ->
            Image(
                painter = painterResource(id = R.drawable.world_map),
                contentDescription = null,
                modifier = Modifier
                    .offset(x = offsetX.dp)
                    .scale(1.8f),
                contentScale = ContentScale.Crop
            )
            Card(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Column {
                    Text(
                        text = "Welcome to Wait What!?",
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Test your knowledge of the flags of the world!",
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(onNavigateToGameScreen = {})
}