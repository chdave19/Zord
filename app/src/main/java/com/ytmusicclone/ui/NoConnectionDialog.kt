package com.ytmusicclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ytmusicclone.utility.android.AndroidNetwork
import com.ytmusicclone.utility.ui.DragAnchors
import kotlinx.coroutines.launch

@Composable
fun NoConnectionDialog(
    modifier: Modifier = Modifier,
    anchoredDraggableState: AnchoredDraggableState<DragAnchors>
) {
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp
    val density = LocalDensity.current
    val totalDistance = with(density) { maxHeight.toPx() - 120.dp.toPx() }
    val yOffset = with(density) { 120.dp.toPx() }
    val maxWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val xOffset = with(density) { maxWidthDp.toPx() }
    val swipeState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.LEFT,
            anchors = DraggableAnchors {
                DragAnchors.LEFT at 0f
                DragAnchors.RIGHT at (xOffset + 50f)
            }
        )
    }
    val alpha by remember {
        derivedStateOf { 1 - (swipeState.requireOffset() / (xOffset + 50f)) }
    }
    val networkIsAvailable by AndroidNetwork.networkStatus.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(networkIsAvailable) {
        swipeState.snapTo(DragAnchors.LEFT)
    }

    if(!networkIsAvailable){
        Column(
            modifier
                .graphicsLayer(
                    translationY = if (anchoredDraggableState.requireOffset() < totalDistance) 0f else -yOffset,
                    translationX = swipeState.requireOffset(),
                    alpha = alpha
                )
                .fillMaxWidth()
                .height(132.dp)
                .background(Color(52, 51, 51))
                .padding(12.dp)
                .anchoredDraggable(
                    state = swipeState,
                    orientation = Orientation.Horizontal
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text(
                    "No connection",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text("Looks like you are offline", color = Color.LightGray, fontSize = 14.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {
                    scope.launch{
                        swipeState.animateTo(DragAnchors.RIGHT)
                    }
                }) {
                    Text("Dismiss", color = Color.White, fontSize = 16.sp)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Go to downloads", color = Color.Black, fontSize = 14.sp)
                }
            }
        }
    }
}