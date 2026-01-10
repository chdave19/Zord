package com.ytmusicclone.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ytmusicclone.composables.SpeedDial
import com.ytmusicclone.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val listState = LazyListState()
    val isRefreshingData by viewModel.dataIsRefreshing.collectAsStateWithLifecycle()
    val refreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        state = refreshState,
        modifier = modifier.fillMaxSize(),
        isRefreshing = isRefreshingData,
        onRefresh = {
            viewModel.refreshTracks()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                items(3, {it}) {setIndex ->
                    val speedDialState by viewModel.tracks.collectAsStateWithLifecycle()
                    if(speedDialState.dataFetchingCompleted){
                        SpeedDial(
                            musicListSet = speedDialState.trackList
                        )
                    }else{
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}


