package com.alvaromr.mypunkbeers.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Navigator : ViewModel() {
    var commands: MutableStateFlow<NavigationCommand> = MutableStateFlow(NavigationCommand.None)

    fun navigate(command: NavigationCommand) {
        commands.value = command
    }
}

