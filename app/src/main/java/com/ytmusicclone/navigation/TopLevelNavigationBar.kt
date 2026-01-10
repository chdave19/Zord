package com.ytmusicclone.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
