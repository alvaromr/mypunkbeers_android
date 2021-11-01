package com.alvaromr.mypunkbeers.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaromr.mypunkbeers.R
import com.alvaromr.mypunkbeers.ui.components.BeerImage
import com.alvaromr.mypunkbeers.ui.components.CollectEffects
import com.alvaromr.mypunkbeers.ui.navigation.NavigationCommand
import com.alvaromr.mypunkbeers.ui.navigation.Navigator
import com.alvaromr.mypunkbeers.ui.screen.Screen
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailContract.Effect
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailContract.Event

object BeerDetailScreen : Screen {
    @Composable
    override fun TopBar() {
        val viewModel: BeerDetailViewModel = hiltViewModel()

        val contentColor = MaterialTheme.colors.onPrimary

        val state = viewModel.viewState
        val triggerEvent = viewModel::triggerEvent

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primarySurface,
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier
                        .clickable { triggerEvent(Event.Back) }
                        .padding(15.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = contentColor
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
    override fun Content() {
        val viewModel: BeerDetailViewModel = hiltViewModel()
        val beer = viewModel.viewState.data.beer
        if (beer != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(5.dp)
                )
                Text(
                    text = beer.subtitle,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = beer.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(5.dp)
                )
                BeerImage(
                    url = beer.imageUrl,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .height(150.dp)
                        .width(100.dp)
                )
            }
        }
    }

    @Composable
    override fun Effects(navigator: Navigator) {
        val viewModel: BeerDetailViewModel = hiltViewModel()

        val context = LocalContext.current

        CollectEffects(effects = viewModel.effects, action = { effect ->
            when (effect) {
                is Effect.NavigateBack -> {
                    navigator.navigate(NavigationCommand.Back)
                }
                is Effect.NotFoundToast -> {
                    Toast.makeText(context, R.string.generic_error, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}