package com.azelder.waitwhat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.azelder.waitwhat.game.ui.GameRoute
import com.azelder.waitwhat.game.ui.HomeScreen
import com.azelder.waitwhat.game.ui.LeaderboardRoute
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Game

@Serializable
object Leaderboard

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
                    navHostController.navigate(Leaderboard,
                        navOptions = navOptions {
                            popUpTo(Home) { inclusive = false }
                        })
                }
            )
        }
        composable<Leaderboard> {
            LeaderboardRoute(
                onNavigateToHome = {
                    navHostController.navigate(route = Home)
                }
            )
        }
    }
}