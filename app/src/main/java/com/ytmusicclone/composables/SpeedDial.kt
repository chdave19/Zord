package com.ytmusicclone.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ytmusicclone.data.model.Track

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeedDial(
    musicListSet: List<List<Track>> = emptyList()
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            ProfilePhoto("https://images.pexels.com/photos/26100579/pexels-photo-26100579.jpeg")
            Spacer(Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}) {
                Column {
                    Text("CHIGOZIE DAVID", color = Color.Gray, fontSize = 10.sp)
                    Text("Speed dial", fontSize = 18.sp)
                }
                Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        val lazyRowState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        val flingBehavior = rememberSnapFlingBehavior(lazyRowState)
        LazyRow(modifier = Modifier.fillMaxWidth(), state = lazyRowState, flingBehavior = flingBehavior, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(3, {it}) {setIndex->
                BoxWithConstraints(modifier = Modifier.fillParentMaxWidth()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth().height(340.dp),
                    ) {
                        items(musicListSet[setIndex], { it.trackId }){music ->
                            FastImage(Modifier.height(100.dp), music.trackCoverXl)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}