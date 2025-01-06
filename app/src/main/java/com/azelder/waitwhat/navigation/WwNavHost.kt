package com.azelder.waitwhat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.azelder.waitwhat.game.dnd.ui.EndGameRoute
import com.azelder.waitwhat.game.dnd.ui.GameRoute
import com.azelder.waitwhat.game.dnd.ui.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Game

@Serializable
object EndGame

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
                onNavigateBack = { navHostController.popBackStack() },
                onNavigateToEndScreen = {
                    navHostController.navigate(EndGame,
                        navOptions = navOptions {
                            popUpTo(Home) { inclusive = false }
                        })
                }
            )
        }
        composable<EndGame> {
            EndGameRoute(
                onNavigateToHome = {
                    navHostController.navigate(route = Home)
                }
            )
        }
    }
}