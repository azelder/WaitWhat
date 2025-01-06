package com.azelder.waitwhat.game.dnd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azelder.waitwhat.R
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun HomeScreen(onNavigateToGameScreen: () -> Unit) {
    WaitWhatTheme {
        Surface {
            Image(
                painter = painterResource(id = R.drawable.dnd_yawning_portal_splash_image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {

                 Button(
                     modifier = Modifier
                         .fillMaxWidth()
                         .align(Alignment.CenterHorizontally)
                         .background(Color.Transparent),
                     elevation = ButtonDefaults.elevatedButtonElevation(),
                     onClick = { onNavigateToGameScreen() },
                     colors = ButtonDefaults.buttonColors(
                         containerColor = MaterialTheme.colorScheme.tertiary
                     )
                 ) {
                     Text(text = "Lets play DnD Monster Who Dis")
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