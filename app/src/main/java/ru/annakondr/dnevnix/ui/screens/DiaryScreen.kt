package ru.annakondr.dnevnix.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.annakondr.dnevnix.navigation.NavigationManager

@Composable
fun DiaryScreenUi(modifier : Modifier, navigationManager: NavigationManager){
    Text(modifier = modifier, text = "Дневник")
}