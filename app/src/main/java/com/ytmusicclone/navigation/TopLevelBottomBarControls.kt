package com.ytmusicclone.navigation

import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ytmusicclone.ui.NoConnectionDialog
import com.ytmusicclone.utility.ui.BottomMiniPlayer
import com.ytmusicclone.utility.ui.DragAnchors
import com.ytmusicclone.utility.ui.MiniPlayerMusicProgressBar

@Composable
fun BoxScope.BottomBarControls() {
    val density = LocalDensity.current
    val maxHeight =
        LocalConfiguration.current.screenHeightDp.dp
    val navBarHeightPx = with(density) { 56.dp.toPx() }
    val maxHeightDp = remember { maxHeight }
    val maxHeightPx = remember { with(density) { maxHeightDp.toPx() } }
    val minHeightPx = remember { with(density) { 88.dp.toPx() } }
    val requiredTravelDistance = maxHeightPx - minHeightPx
    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.COLLAPSED,
            anchors = DraggableAnchors {
                DragAnchors.EXPANDED at 0f
                DragAnchors.COLLAPSED at requiredTravelDistance
            },
        )
    }
    val showMusicProgressBar by remember { derivedStateOf { anchoredDraggableState.requireOffset() >= requiredTravelDistance } }

    BottomMiniPlayer(anchoredDraggableState = anchoredDraggableState)
    NoConnectionDialog(Modifier.align(Alignment.BottomEnd), anchoredDraggableState)
    Column(
        modifier = Modifier
            .graphicsLayer(
                translationY = (1 - (anchoredDraggableState.requireOffset() / requiredTravelDistance)) * navBarHeightPx
            )
            .fillMaxWidth()
            .align(Alignment.BottomEnd)
            .wrapContentHeight()
    ) {
        if (showMusicProgressBar)
            MiniPlayerMusicProgressBar()
        TopLevelNavigationBar()
    }
}