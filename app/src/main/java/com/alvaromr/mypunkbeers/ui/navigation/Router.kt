package com.alvaromr.mypunkbeers.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alvaromr.mypunkbeers.ui.theme.MyPunkBeersTheme

@Composable
fun Router(directions: List<NavDirection>) {

    val navController = rememberNavController()

    val navigator: Navigator = hiltViewModel()

    navigator.commands.collectAsState().value.also { command ->
        when (command) {
            is NavigationCommand.RoutedNavigationCommand -> {
                navController.navigate(command.route)
            }
            is NavigationCommand.Back -> navController.popBackStack()
            is NavigationCommand.None -> {
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = directions.first().routeConfig
    ) {
        directions.forEach { direction ->
            composable(direction.routeConfig) {
                val screen = direction.screen
                screen.Effects(navigator = navigator)
                MyPunkBeersTheme {
                    Scaffold(
                        topBar = {
                            screen.TopBar()
                        },
                        content = {
                            screen.Content()
                        },
                    )
                }
            }
        }
    }
}