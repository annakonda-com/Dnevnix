package ru.annakondr.dnevnix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.annakondr.dnevnix.R
import ru.annakondr.dnevnix.navigation.NavigationManager
import ru.annakondr.dnevnix.ui.entities.Elephant
import ru.annakondr.dnevnix.ui.entities.Lesson

@Composable
fun LessonScreenUi(modifier: Modifier, navigationManager: NavigationManager, lesson: Lesson) {
    var showDialog by remember { mutableStateOf(false) }
    var showCongratDialog by remember { mutableStateOf(false) }
    var lessonGoing by remember { mutableStateOf(false) }
    var duration by remember { mutableStateOf(lesson.time) }
    var newAmount by remember { mutableStateOf( 0)}
    Column(modifier.verticalScroll(rememberScrollState()), verticalArrangement = spacedBy(16.dp)) {
        Text("Задание от учителя:", style = MaterialTheme.typography.titleMedium)
        Text(
            lesson.task, style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceContainerLowest,
                    RoundedCornerShape(50)
                )
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text("Дополнить задание*", style = MaterialTheme.typography.titleMedium)
        var text by remember { mutableStateOf(lesson.ownTask ?: "") }
        TextField(
            value = text,
            onValueChange = {
                text = it
                lesson.ownTask = text
            },
            label = {},
            placeholder = { },
            trailingIcon = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Text("*сохраняется автоматически", style = MaterialTheme.typography.bodySmall)
        Button(
            onClick = {
                showDialog = true
            }, shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.mic), "",
                    modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.inversePrimary
                )
                Text("Ввести голосом", style = MaterialTheme.typography.headlineMedium)
            }
        }
        Image(
            painter = painterResource(R.drawable.elephant), "",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
        )

        if (duration != 0) {
            Text(
                text = "$duration сек",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        if (!lessonGoing) {
            Button(
                onClick = {
                    lessonGoing = true
                }, shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, (-18).dp)
            ) {
                Text("Начать выполнение", style = MaterialTheme.typography.headlineMedium)
            }
        } else {
            LaunchedEffect(lessonGoing) {
                while (true) {
                    duration++
                    delay(1000L)
                }
            }
            Button(
                onClick = {
                    lessonGoing = false
                    Elephant.money.intValue =
                        Elephant.money.intValue + ((duration - lesson.time) / 36)
                    newAmount = (duration - lesson.time) / 36
                    lesson.time = duration
                    showCongratDialog = true

                }, shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, (-18).dp)
            ) {
                Text("Завершить выполнение", style = MaterialTheme.typography.headlineMedium)
            }
        }

    }
    if (showDialog) {
        AlertDialog(
            title = { Text(text = "Пока недоступно") },
            onDismissRequest = {}, confirmButton = {
                OkButton {
                    showDialog = false
                }
            })
    }
    if (showCongratDialog) {
        AlertDialog(
            title = {
                if (newAmount == 0) {
                    Text(text = "Вы не заработали монет!")
                } else {
                    Text(text = "Вы хорошо потрудились!")
                }
            },
            text = {
                if (newAmount == 0) {
                    Text(text = "Чтобы заработать 1 монету, нужно потратить не меньше 36 секунд подряд на ДЗ!")
                } else {
                    Text(text = "Вы заработали $newAmount монет, не забудьте покормить слоника ;)")
                }
            },
            onDismissRequest = {}, confirmButton = {
                OkButton {
                    showCongratDialog = false
                }
            })
    }
}