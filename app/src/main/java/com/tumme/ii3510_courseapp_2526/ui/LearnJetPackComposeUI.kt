package com.tumme.ii3510_courseapp_2526.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tumme.ii3510_courseapp_2526.R


@Preview(showBackground = true)
@Composable
fun InputExample() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(stringResource(R.string.label_enter_name)) }
    )
}


@Composable
fun IsepCompose() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.jetpack_compose_icon),
            contentDescription = stringResource(R.string.content_description_jetpack_logo),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.text_isep_compose),
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Yellow)
                .fillMaxWidth()
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun LayoutExample() {
    Row {
        Button(onClick = {}) { Text(stringResource(R.string.button_left)) }
        Button(onClick = {}) { Text(stringResource(R.string.button_right)) }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.text_inside_box))
    }
}

//@Preview(showBackground = true)
@Composable
fun ColumnExample() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.text_top))
        Button(onClick = {}) { Text(stringResource(R.string.button_click_me)) }
        Text(stringResource(R.string.text_bottom))
    }
}

@Composable
fun Greeting(name: String) {
    Text(stringResource(R.string.greeting, name))
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("ISEP Student")
}