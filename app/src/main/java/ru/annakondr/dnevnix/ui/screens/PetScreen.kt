package ru.annakondr.dnevnix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.annakondr.dnevnix.R
import ru.annakondr.dnevnix.navigation.NavigationManager
import ru.annakondr.dnevnix.ui.entities.Elephant

@Composable
fun PetScreenUi(modifier : Modifier, navigationManager: NavigationManager){
    var showDialog by remember { mutableStateOf(false) }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(8.dp).fillMaxSize()){
        Row(Modifier.
        background(MaterialTheme.colorScheme.surfaceContainerLowest, RoundedCornerShape(40.dp)).
        border(2.dp, color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(40.dp)).padding(8.dp)
            .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(R.drawable.money), contentDescription = "",
                modifier = Modifier.size(35.dp))
            Text(Elephant.money.intValue.toString(), style= MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(20.dp, 0.dp))
        }
        if (Elephant.mood.intValue >= 60) {
            Image(
                painter = painterResource(R.drawable.elephant), "",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.sad_elephant), "",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(painter = painterResource(R.drawable.mood), "",
                modifier = Modifier.size(70.dp), tint = MaterialTheme.colorScheme.inversePrimary)
            Text(Elephant.mood.intValue.toString(), style= MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(20.dp, 0.dp))
        }
        Button(onClick = {
            if (Elephant.CanFeed()){
                Elephant.FeedElephant()
            } else {
                showDialog = true
            }
        }, shape = RoundedCornerShape(24.dp)) {
            Row() {
                Icon(painter = painterResource(R.drawable.bananas_svgrepo_com), "",
                    modifier = Modifier.size(70.dp), tint = MaterialTheme.colorScheme.inversePrimary)
                Column() {
                    Text("ПОКОРМИТЬ", style= MaterialTheme.typography.headlineLarge)
                    Text("20 монет", style= MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
    if (showDialog){
        AlertDialog( title = { Text(text = "Нет монет!") },
            text = { Text(text = "У вас недостаточно монеток, чтобы покормить слоника!") },
            onDismissRequest={}, confirmButton={ OkButton{
                showDialog = false
            } } )
    }
}