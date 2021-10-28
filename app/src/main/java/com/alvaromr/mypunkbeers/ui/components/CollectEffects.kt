package com.alvaromr.mypunkbeers.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

@Composable
fun <E> CollectEffects(
    effects: Flow<E> = flowOf(),
    action: suspend (E) -> Unit = {}
) {
    LaunchedEffect(true) {
        effects.onEach(action).collect()
    }
}