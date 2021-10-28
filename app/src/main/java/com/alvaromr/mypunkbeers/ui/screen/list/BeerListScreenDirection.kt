package com.alvaromr.mypunkbeers.ui.screen.list

import com.alvaromr.mypunkbeers.ui.navigation.NavDirection
import com.alvaromr.mypunkbeers.ui.navigation.NavigationCommand
import com.alvaromr.mypunkbeers.ui.screen.Screen

object BeerListScreenDirection : NavDirection {

    override val routeConfig = "BeerListScreen"

    override val screen: Screen = BeerListScreen

    val destination = object : NavigationCommand.RoutedNavigationCommand {
        override val route = routeConfig
    }
}