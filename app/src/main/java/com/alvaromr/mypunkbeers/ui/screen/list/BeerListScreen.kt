package com.alvaromr.mypunkbeers.ui.screen.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

object BeerListScreen {
    @Composable
    fun Content() {
        val viewModel: BeerListViewModel = hiltViewModel()

        Surface(color = MaterialTheme.colors.background) {
            Text(text = viewModel.currentState.toString())
        }
    }
}