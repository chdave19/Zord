package com.ytmusicclone.utility.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.ytmusicclone.R
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ProfilePhoto(imagePath: String) {
    AsyncImage(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable {},
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.youtube_music_logo),
    )
}

enum class DragAnchors { COLLAPSED, EXPANDED, LEFT, RIGHT }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMiniPlayer(
    modifier: Modifier = Modifier,
    anchoredDraggableState: AnchoredDraggableState<DragAnchors>
) {
    val scope = rememberCoroutineScope()
    val moreDetailsScaffoldState = rememberBottomSheetScaffoldState()

    //This BackHandler should only intercepts events when the sheet is expanded
    BackHandler(anchoredDraggableState.currentValue == DragAnchors.EXPANDED) {
        scope.launch {
            anchoredDraggableState.animateTo(DragAnchors.COLLAPSED, tween(400))
        }
    }
    //This BackHandler should only intercepts events when this sheet is expanded
    BackHandler(moreDetailsScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
        scope.launch {
            moreDetailsScaffoldState.bottomSheetState.partialExpand()
        }
    }

    Box(
        modifier = modifier
            .graphicsLayer(
                translationY = anchoredDraggableState.requireOffset()
            )
            .anchoredDraggable(anchoredDraggableState, Orientation.Vertical)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        val color = remember { Color(Random.nextInt(255), Random.nextInt(255),Random.nextInt(255)) }
        BottomSheetScaffold(
            scaffoldState = moreDetailsScaffoldState,
            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            modifier = Modifier.fillMaxSize(),
            sheetDragHandle = {},
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.87f)
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    var currentTabIndex by remember { mutableIntStateOf(0) }
                    val tabItems = listOf("UP NEXT", "LYRICS", "RELATED")
                    SecondaryTabRow(
                        containerColor = Color.Transparent,
                        selectedTabIndex = currentTabIndex,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        indicator = {
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(currentTabIndex, matchContentSize = false),
                                color = Color.White
                            )
                        },
                        divider = {
                            HorizontalDivider(color = Color(137, 136, 140, 66))
                        }
                    ) {
                        tabItems.forEachIndexed { index, title ->
                            Tab(
                                selected = currentTabIndex == index,
                                onClick = {
                                    currentTabIndex = index
                                    if(moreDetailsScaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded){
                                        scope.launch {
                                            moreDetailsScaffoldState.bottomSheetState.expand()
                                        }
                                    }
                                },
                                unselectedContentColor = Color.Gray,
                                selectedContentColor = Color.White,
                            ) {
                                Text(title, modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            },
            sheetPeekHeight = 80.dp,
            containerColor = MaterialTheme.colorScheme.secondary,
            sheetContainerColor = color,
            sheetSwipeEnabled = true
        ) {}
    }
}

@Composable
fun MiniPlayerMusicProgressBar() {
    var progress by remember { mutableFloatStateOf(0.5f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            color = Color.White,
            trackColor = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}