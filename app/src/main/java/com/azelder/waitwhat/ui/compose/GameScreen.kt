package com.azelder.waitwhat.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.azelder.waitwhat.ui.theme.WaitWhatTheme

@Composable
fun GameScreen(onNavigateBack: () -> Unit) {
    WaitWhatTheme {
        Surface {
            Column {
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