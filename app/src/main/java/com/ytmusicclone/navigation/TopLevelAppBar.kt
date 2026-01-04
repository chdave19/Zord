package com.ytmusicclone.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ytmusicclone.R
import com.ytmusicclone.utility.ui.ProfilePhoto

@Composable
fun TopLevelAppBar() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 12.dp, start = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(R.drawable.youtube_music_logo),
                    contentDescription = "Youtube music logo"
                )
                Text(
                    "Music",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(52.dp)
                ) {
                    BadgedBox(
                        badge = {
                            Badge(containerColor = Color.Red) {
                                Text("9+")
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Spacer(Modifier.width(4.dp))
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(52.dp)
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(Modifier.width(4.dp))
                ProfilePhoto("https://images.pexels.com/photos/26100579/pexels-photo-26100579.jpeg")
                //https://images.pexels.com/photos/26100579/pexels-photo-26100579.jpeg
            }
        }
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(AppNavigation.homePageTopNavigations, { it.key }) { navItem ->
                Button(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    onClick = {},
                    contentPadding = PaddingValues(
                        horizontal = 8.dp,
                        vertical = 1.dp
                    )
                ) {
                    Text("${navItem.title}")
                }
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}
