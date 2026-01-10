package com.ytmusicclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ytmusicclone.navigation.AppNavigation
import com.ytmusicclone.navigation.BottomBarControls
import com.ytmusicclone.navigation.Destinations
import com.ytmusicclone.navigation.TopLevelAppBar
import com.ytmusicclone.navigation.toEntries
import com.ytmusicclone.viewmodels.HomeScreenViewModel
import com.ytmusicclone.viewmodels.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()){
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopLevelAppBar()
            },
            snackbarHost = {
                SnackbarHost(AppNavigation.snackbarState)
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) { mainPaddingValues ->
            NavDisplay(
                onBack = { AppNavigation.navigator.goBack() },
                entries = AppNavigation.navigationState.toEntries(
                    entryProvider = entryProvider {
                        entry<Destinations.Home> {
                            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel, HomeScreenViewModel.Factory>(
                                creationCallback = { factory ->
                                    factory.create(Destinations.Home)
                                }
                            )
                            HomeScreen(
                                Modifier.padding(
                                    top = mainPaddingValues.calculateTopPadding(),
                                    bottom = 112.dp
                                ),
                                viewModel = homeScreenViewModel
                            )
                        }
                        entry<Destinations.Samples> {
                            SamplesScreen(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Blue)
                                    .padding(
                                        top = mainPaddingValues.calculateTopPadding(),
                                        bottom = 112.dp
                                    )
                            )
                        }
                        entry<Destinations.Explore> {
                            ExploreScreen()
                        }
                        entry<Destinations.Library> {
                            LibraryScreen()
                        }
                    }
                )
            )
        }
        val showDialog by viewModel.showDialog.collectAsState()
        BottomBarControls(
            showDialog,
            viewModel::closeDialog
        )
    }
}