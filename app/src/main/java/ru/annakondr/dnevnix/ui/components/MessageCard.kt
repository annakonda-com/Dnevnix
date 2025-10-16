package ru.annakondr.dnevnix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageCard(color: Color, text: String, textColor: Color, modifier: Modifier){
    Box(
        modifier = modifier.widthIn(25.dp,  320.dp).background(color, RoundedCornerShape(24.dp))
    ){
        Text(text, modifier = Modifier.padding(16.dp), color = textColor)
    }
}
