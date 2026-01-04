package com.ytmusicclone.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.ytmusicclone.screens.ExploreScreen
import com.ytmusicclone.screens.HomeScreen
import com.ytmusicclone.screens.LibraryScreen
import com.ytmusicclone.screens.SamplesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavDisplay() {
    NavDisplay(
        backStack = AppNavigation.topBackStack,
        onBack = { AppNavigation.topBackStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(tween(500)) { it } togetherWith ExitTransition.KeepUntilTransitionsFinished
        },
        popTransitionSpec = {
            slideInHorizontally(tween(500)) { -it } togetherWith slideOutHorizontally(tween(500)) { -it }
        },
        entryProvider = entryProvider {
            entry<Destinations.ViewImageScreen> { imageDetails ->
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
                            title = {},
                            navigationIcon = {
                                IconButton(onClick = {
                                    AppNavigation.topBackStack.removeLastOrNull()

                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Go back",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    }
                ) {
                    Box(
                        Modifier
                            .padding(
                                top = it.calculateTopPadding(),
                                bottom = it.calculateBottomPadding()
                            )
                            .fillMaxSize()
                            .background(Color.Black), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageDetails.url)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
            entry<Destinations.MainScreen>(

            ) {
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
                                        HomeScreen(
                                            Modifier.padding(
                                                top = mainPaddingValues.calculateTopPadding(),
                                                bottom = 112.dp
                                            )
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
                    BottomBarControls()
                }
            }
        }
    )
}