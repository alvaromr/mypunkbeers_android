package com.alvaromr.mypunkbeers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListScreen
import com.alvaromr.mypunkbeers.ui.theme.MyPunkBeersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPunkBeersTheme {
                BeerListScreen.Content()
            }
        }
    }
}