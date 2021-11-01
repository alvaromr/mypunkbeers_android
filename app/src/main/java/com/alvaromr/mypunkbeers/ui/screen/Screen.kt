package com.alvaromr.mypunkbeers.ui.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.alvaromr.mypunkbeers.ui.navigation.Navigator

interface Screen {
    @Composable
    fun TopBar()

    @Composable
    fun Content()

    @Composable
    fun Effects(navigator: Navigator)

    @Composable
    fun Args(arguments: Bundle?)
}