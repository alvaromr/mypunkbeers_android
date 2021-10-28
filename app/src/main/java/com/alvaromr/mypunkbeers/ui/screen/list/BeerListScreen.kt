package com.alvaromr.mypunkbeers.ui.screen.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaromr.mypunkbeers.R
import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.ui.components.CollectEffects
import com.alvaromr.mypunkbeers.ui.navigation.Navigator
import com.alvaromr.mypunkbeers.ui.screen.Screen
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailScreenDirection

object BeerListScreen : Screen {

    @Composable
    override fun TopBar() {
        val viewModel: BeerListViewModel = hiltViewModel()

        val contentColor = MaterialTheme.colors.onPrimary

        val state = viewModel.currentState
        val triggerEvent = viewModel::triggerEvent

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primarySurface,
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BeerSearchTextField(
                    value = state.viewState.query,
                    onValueChange = {
                        triggerEvent(BeerListContract.Event.QueryChanged(it))
                    },
                    contentColor = contentColor
                )
                if (state.loading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }

    @Composable
    private fun BeerSearchTextField(
        value: String,
        onValueChange: (String) -> Unit,
        contentColor: Color
    ) {
        val localFocusManager = LocalFocusManager.current
        if (value.isEmpty()) {
            localFocusManager.clearFocus()
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = stringResource(R.string.search),
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onValueChange("")
                    },
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = contentColor,
                textColor = contentColor,
                focusedLabelColor = contentColor,
                leadingIconColor = contentColor,
                trailingIconColor = contentColor,
                unfocusedLabelColor = contentColor,
                unfocusedIndicatorColor = contentColor
            )
        )
    }

    @Composable
    override fun Content() {
        val viewModel: BeerListViewModel = hiltViewModel()

        val beers = viewModel.currentState.viewState.beers
        val query = viewModel.currentState.viewState.query
        val loading = viewModel.currentState.loading
        if (beers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (query.isBlank() || loading) {
                        stringResource(id = R.string.nothing_searched_yet)
                    } else {
                        stringResource(id = R.string.no_beers_with_name, query)
                    }
                )
            }
        } else {
            BeerList(
                beers = beers,
                onBeerClick = { viewModel.triggerEvent(BeerListContract.Event.BeerClicked(it)) },
                onListEndReached = {
                    viewModel.triggerEvent(
                        BeerListContract.Event.ListScrolledToEndPosition(
                            it
                        )
                    )
                }
            )
        }
    }

    @Composable
    private fun BeerList(
        beers: List<Beer>,
        onBeerClick: (Beer) -> Unit,
        onListEndReached: (Int) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(beers) { i, item ->
                if (i >= beers.lastIndex) {
                    onListEndReached(i)
                }
                BeerRow(item, onBeerClick)
            }
        }
    }

    @Composable
    private fun BeerRow(beer: Beer, onBeerClick: (Beer) -> Unit) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clickable(onClick = { onBeerClick(beer) }),
            elevation = 2.dp,
            backgroundColor = MaterialTheme.colors.surface,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = beer.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = beer.subtitle,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

    @Composable
    override fun Effects(navigator: Navigator) {
        val viewModel: BeerListViewModel = hiltViewModel()

        val context = LocalContext.current

        CollectEffects(effects = viewModel.effects, action = { effect ->
            when (effect) {
                is BeerListContract.Effect.NavigateToBeerDetail -> {
                    navigator.navigate(BeerDetailScreenDirection.destination(effect.id))
                }

                is BeerListContract.Effect.ErrorToast -> {
                    Toast.makeText(context, R.string.generic_error, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}