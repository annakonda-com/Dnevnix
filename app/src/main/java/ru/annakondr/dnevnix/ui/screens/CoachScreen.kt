package ru.annakondr.dnevnix.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.annakondr.dnevnix.R
import ru.annakondr.dnevnix.navigation.NavigationManager

@Preview
@Composable
fun CoachScreenUi(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        TextField(
            value = "",
            onValueChange = {},
            label = {},
            placeholder = { Text(text = stringResource(R.string.help)) }
        )
    }
}