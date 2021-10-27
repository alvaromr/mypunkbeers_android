package com.alvaromr.mypunkbeers.ui.screen.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

object BeerListScreen {
    @Composable
    fun Content() {
        val viewModel: BeerListViewModel = hiltViewModel()

        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.currentState.viewState.query,
                    onValueChange = {
                        viewModel.triggerEvent(BeerListContract.Event.QueryChanged(it))
                    }
                )
                if(viewModel.currentState.loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                LazyColumn {
                    itemsIndexed(viewModel.currentState.viewState.beers) { i, item ->
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}