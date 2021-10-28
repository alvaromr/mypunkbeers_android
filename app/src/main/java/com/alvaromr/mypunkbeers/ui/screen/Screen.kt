package com.alvaromr.mypunkbeers.ui.screen

import androidx.compose.runtime.Composable
import com.alvaromr.mypunkbeers.ui.navigation.Navigator

interface Screen {
    @Composable
    fun TopBar()

    @Composable
    fun Content()

    @Composable
    fun Effects(navigator: Navigator)
}