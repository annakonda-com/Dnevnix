package ru.annakondr.dnevnix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.annakondr.dnevnix.navigation.LessonScreen
import ru.annakondr.dnevnix.navigation.NavigationManager
import ru.annakondr.dnevnix.ui.entities.Lesson

@Composable
fun ClassCard(color: Color, subject: String, task: String, icon: Int, lesson: Lesson, navigationManager: NavigationManager,){
    Row (
        modifier = Modifier.fillMaxWidth()
            .background(color, RoundedCornerShape(18.dp)).padding(16.dp)
            .clickable(onClick = { navigationManager.route(LessonScreen(lesson)) }),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (modifier = Modifier.weight(1f)) {
            Text(subject, style = MaterialTheme.typography.titleLarge)
            Text(task, style = MaterialTheme.typography.titleSmall)
        }
        IconButton(onClick = { navigationManager.route(LessonScreen(lesson)) }) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
