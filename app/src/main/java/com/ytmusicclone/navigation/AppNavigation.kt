package com.ytmusicclone.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import com.woowla.compose.icon.collections.fontawesome.FontAwesome
import com.woowla.compose.icon.collections.fontawesome.fontawesome.Solid
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Bookmark
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Compass
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.House
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Play
import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        if (route in state.backStacks.keys) {
            // This is a top level route, just switch to it
            state.topLevelRoute = route
        } else {
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")
        currentStack.removeLastOrNull()
    }
}


//NavigationState.kt
/**
 * Create a navigation state that persists config changes and process death.
 */
@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationState {

    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer())
    ) {
        mutableStateOf(startRoute)
    }

    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}

/**
 * State holder for navigation state.
 *
 * @param startRoute - the start route. The user will exit the app through this route.
 * @param topLevelRoute - the current top level route
 * @param backStacks - the back stacks for each top level route
 */
class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute: NavKey by topLevelRoute
}

/**
 * Convert NavigationState into NavEntries.
 */
@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {

    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }
//    return stacksInUse
//        .flatMap { decoratedEntries[it] ?: emptyList() }
//        .toMutableStateList()
    return (decoratedEntries[topLevelRoute]?.toMutableStateList()
        ?: emptyList()) as SnapshotStateList<NavEntry<NavKey>>
}


object AppNavigation {
    val topBackStack = NavBackStack<NavKey>(Destinations.MainScreen)
    val snackbarState = SnackbarHostState()
    lateinit var navigator: Navigator
    lateinit var navigationState: NavigationState
    val homePageTopNavigations = listOf(
        TopNavs("Energize"),
        TopNavs("Feel good"),
        TopNavs("Relax"),
        TopNavs("Party"),
        TopNavs("Workout"),
        TopNavs("Commute"),
        TopNavs("Romance"),
        TopNavs("Sad"),
        TopNavs("Focus"),
        TopNavs("Sleep")
    )

    private val images = listOf(
        "https://images.pexels.com/photos/34917999/pexels-photo-34917999.jpeg",
        "https://images.pexels.com/photos/1024975/pexels-photo-1024975.jpeg",
        "https://images.pexels.com/photos/1535244/pexels-photo-1535244.jpeg",
        "https://images.pexels.com/photos/1687845/pexels-photo-1687845.jpeg",
        "https://images.pexels.com/photos/815880/pexels-photo-815880.jpeg",
        "https://images.pexels.com/photos/1718345/pexels-photo-1718345.jpeg"
    )

    @OptIn(ExperimentalUuidApi::class)
    val listImages = Array(9) {
        Uuid.random().toString() to images[Random.nextInt(images.size)]
    }
}

data class TopNavs @OptIn(ExperimentalUuidApi::class) constructor(
    val title: String,
    val key: String = Uuid.random()
        .toString()
)

data class NavItem(
    val destination: TopLevelDestinations,
    val icon: ImageVector
)

enum class TopLevelDestinations(val title: String, val destinationKey: NavKey) {
    HOME("Home", Destinations.Home),
    SAMPLES("Samples", Destinations.Samples),
    EXPLORE("Explore", Destinations.Explore),
    LIBRARY("Library", Destinations.Library)
}

@Serializable
sealed interface Destinations : NavKey {
    @Serializable
    data object Home : Destinations

    @Serializable
    data object Samples : Destinations

    @Serializable
    data object Explore : Destinations

    @Serializable
    data object Library : Destinations

    @Serializable
    data object MainScreen : Destinations

    @Serializable
    data object SettingsScreen : Destinations

    @Serializable
    data class ViewImageScreen(val url: String) : Destinations
}

val icons = listOf(
    NavItem(TopLevelDestinations.HOME, FontAwesome.Solid.House),
    NavItem(TopLevelDestinations.SAMPLES, FontAwesome.Solid.Play),
    NavItem(TopLevelDestinations.EXPLORE, FontAwesome.Solid.Compass),
    NavItem(TopLevelDestinations.LIBRARY, FontAwesome.Solid.Bookmark)
)