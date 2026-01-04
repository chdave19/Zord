package com.ytmusicclone.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ytmusicclone.ui.SpeedDial

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier) {
    val listState = LazyListState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 16.dp)) {
            items(4, {it}) {
                SpeedDial()
            }
        }
    }
}


