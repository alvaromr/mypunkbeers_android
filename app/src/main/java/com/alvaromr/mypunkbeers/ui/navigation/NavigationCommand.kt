package com.alvaromr.mypunkbeers.ui.navigation

import androidx.navigation.compose.NamedNavArgument

sealed interface NavigationCommand {
    object None : NavigationCommand
    object Back : NavigationCommand

    interface RoutedNavigationCommand : NavigationCommand {
        val arguments: List<NamedNavArgument> get() = listOf()

        val route: String
    }
}