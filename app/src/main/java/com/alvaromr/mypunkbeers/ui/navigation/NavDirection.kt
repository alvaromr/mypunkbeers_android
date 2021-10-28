package com.alvaromr.mypunkbeers.ui.navigation

import com.alvaromr.mypunkbeers.ui.screen.Screen

interface NavDirection {
    val routeConfig: String
    val screen: Screen
}