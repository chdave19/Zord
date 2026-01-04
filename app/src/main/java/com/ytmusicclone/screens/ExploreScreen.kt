package com.ytmusicclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExploreScreen() {
    var index by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyButton { index++ }
        Text("$index")
        MyText()
    }
}

@Composable
fun MyButton(onClick: ()->Unit){
    Button(onClick = onClick) { Text("Click me")}
    Button(onClick = {}) { onClick}
}

@Composable
fun MyText(){
    var index1 by rememberSaveable { mutableIntStateOf(0) }
    var index2 = remember { derivedStateOf { (index1 % 5 == 0) } }

    Text("I might be recompose. Let's find out $index1")
    Text("I might be recompose. Let's find out ${index2.value}")
    Button(onClick = {index1++}) {
        Text("Increment index1")
    }
}