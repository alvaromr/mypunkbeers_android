package com.alvaromr.mypunkbeers.ui.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class Navigator @Inject constructor() : ViewModel() {
    var commands: MutableStateFlow<NavigationCommand> = MutableStateFlow(NavigationCommand.None)

    fun navigate(command: NavigationCommand) {
        commands.value = command
    }
}

