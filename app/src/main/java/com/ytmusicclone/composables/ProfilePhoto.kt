package com.ytmusicclone.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.ytmusicclone.R

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