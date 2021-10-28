package com.alvaromr.mypunkbeers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alvaromr.mypunkbeers.ui.navigation.Router
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailScreenDirection
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListScreenDirection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Router(
                directions = listOf(
                    BeerListScreenDirection,
                    BeerDetailScreenDirection
                )
            )
        }
    }
}