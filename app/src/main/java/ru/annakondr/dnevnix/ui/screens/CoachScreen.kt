package ru.annakondr.dnevnix.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.annakondr.dnevnix.R

import ru.annakondr.dnevnix.ui.components.MessageCard

@Composable
fun CoachScreenUi(modifier: Modifier) {
    val messages = arrayOf(
        "Привет! Я ваш наставник. Моя задача — помочь вам понять сложный материал " +
                "и объяснить тему, если что-то осталось непонятным. Обращайтесь!",
        "Как мне решить уравнение X^2 - 2 = 0?",
        "Давай разберёмся вместе. Ты знаешь, что такое квадрат числа?\n" +
                "Например, что получится, если число умножить само на себя?",
        "А, получается ответ корень из двух и минус корень из двух?",
        "Верно! Молодец, ты справился с задачей."
    )
    var k by remember { mutableStateOf(0) }
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (i in 0..k) {
                if (i % 2 == 0) {
                    MessageCard(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        messages[i], MaterialTheme.colorScheme.onSurface,
                        Modifier.align(Alignment.Start)
                    )
                } else {
                    MessageCard(
                        MaterialTheme.colorScheme.tertiaryContainer, messages[i],
                        MaterialTheme.colorScheme.onTertiaryContainer,
                        Modifier.align(Alignment.End)
                    )
                }
            }
        }
        var text by remember { mutableStateOf("") }
        if (k < 4) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = {},
                placeholder = { Text(text = stringResource(R.string.help)) },
                trailingIcon = {
                    IconButton(onClick = {
                        k+=2
                        text = ""
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.send),
                            contentDescription = ""
                        )
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        } else {
            var showDialog by remember { mutableStateOf(false) }
            Row(Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
                Button(
                    onClick = {
                        showDialog = true;
                    }, shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    colors = ButtonColors(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "пройти тест по теме", modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Button(
                    onClick = { k = 0 }, shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    colors = ButtonColors(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLowest
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "продолжить без теста",
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            if (showDialog){
                AlertDialog( title = { Text(text = "Поздравляем!") },
                    text = { Text(text = "Вы прошли тест и заработали 30 монеток!") },
                    onDismissRequest={}, confirmButton={ OkButton{
                        showDialog = false
                        k = 0
                        // TODO: Монетки прибавить
                    } } )
            }
        }

    }

}

@Composable
fun OkButton(onClick: () -> Unit){
    Button(
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = "Ок", modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}