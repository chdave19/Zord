package com.ytmusicclone.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ytmusicclone.screens.MainScreen
import com.ytmusicclone.screens.ViewImageScreen
import com.ytmusicclone.viewmodels.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavDisplay() {
    NavDisplay(
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = AppNavigation.topBackStack,
        onBack = { AppNavigation.topBackStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(tween(500)) { it } togetherWith ExitTransition.KeepUntilTransitionsFinished
        },
        popTransitionSpec = {
            slideInHorizontally(tween(500)) { -it } togetherWith slideOutHorizontally(tween(500)) { -it }
        },
        entryProvider = entryProvider {
            entry<Destinations.ViewImageScreen> { key ->
                ViewImageScreen(key.url)
            }
            entry<Destinations.MainScreen>(
            ) {
                val viewModel = hiltViewModel<MainScreenViewModel, MainScreenViewModel.Factory>(
                    creationCallback = { factory ->
                        factory.create(Destinations.MainScreen)
                    }
                )
                MainScreen(viewModel = viewModel)
            }
        }
    )
}