package com.ytmusicclone.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.ytmusicclone.R
import com.woowla.compose.icon.collections.fontawesome.FontAwesome
import com.woowla.compose.icon.collections.fontawesome.fontawesome.Solid
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Bookmark
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Compass
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.House
import com.woowla.compose.icon.collections.fontawesome.fontawesome.solid.Play
import com.ytmusicclone.utility.ui.ProfilePhoto
import kotlinx.serialization.Serializable
import androidx.compose.ui.Alignment

@Composable
fun TopLevelNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEach { (destination, icon) ->
            Button(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(0.dp),
                onClick = {
                    when (destination) {
                        TopLevelDestinations.HOME -> {
                            AppNavigation.navigator.navigate(Destinations.Home)
                        }

                        TopLevelDestinations.SAMPLES -> {
                            AppNavigation.navigator.navigate(Destinations.Samples)
                        }

                        TopLevelDestinations.EXPLORE -> {
                            AppNavigation.navigator.navigate(Destinations.Explore)
                        }

                        TopLevelDestinations.LIBRARY -> {
                            AppNavigation.navigator.navigate(Destinations.Library)
                        }
                    }
                },
                colors = ButtonColors(
                    if (destination.destinationKey == AppNavigation.navigationState.topLevelRoute) ButtonDefaults.buttonColors().containerColor else Color.Transparent,
                    Color.White,
                    Color.Unspecified,
                    Color.Unspecified
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = icon,
                        contentDescription = destination.title,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = destination.title, fontSize = 12.sp)
                }
            }
        }
    }
}
