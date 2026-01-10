package com.ytmusicclone.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.ytmusicclone.theme.YtMusicCloneTheme
import com.ytmusicclone.navigation.AppNavigation
import com.ytmusicclone.navigation.Destinations
import com.ytmusicclone.navigation.Navigator
import com.ytmusicclone.navigation.RootNavDisplay
import com.ytmusicclone.navigation.rememberNavigationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("s444sds", "activity created")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navigationState = rememberNavigationState(
                startRoute = Destinations.Home,
                topLevelRoutes = setOf(
                    Destinations.Home, Destinations.Samples, Destinations.Explore,
                    Destinations.Library
                )
            )
            val navigator = remember { Navigator(navigationState) }
            AppNavigation.navigationState = navigationState
            AppNavigation.navigator = navigator
            YtMusicCloneTheme {
                RootNavDisplay()
            }
        }
    }
}