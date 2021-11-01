package com.alvaromr.mypunkbeers.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiToolbar() {
    val systemUiController = rememberSystemUiController()
    val primaryColor = MaterialTheme.colors.primarySurface

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = primaryColor
        )
        systemUiController.setStatusBarColor(
            color = primaryColor
        )
        systemUiController.setNavigationBarColor(
            color = primaryColor
        )
    }
}