package com.azelder.waitwhat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.azelder.waitwhat.game.dnd.ui.GameRoute
import com.azelder.waitwhat.ui.compose.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Game

@Composable
fun WwNavHost(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen(
                onNavigateToGameScreen = { navHostController.navigate(Game) }
            )
        }
        composable<Game> {
            GameRoute(
                onNavigateBack = { navHostController.popBackStack() }
            )
        }
    }
}