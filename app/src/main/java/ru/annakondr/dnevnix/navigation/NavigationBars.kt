package ru.annakondr.dnevnix.navigation


import android.R.attr.title
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import ru.annakondr.dnevnix.R


@Composable
fun NavigationBottomBar(backStack: NavBackStack, navigationManager: NavigationManager) {
    val destinationEntries = destinationsFactory()
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        destinationEntries.forEach { destination ->
            val selected = backStack[backStack.size - 1] == destination.key
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navigationManager.route(path = destination.key)
                },
                icon = {
                    Icon(
                        painter = destination.icon,
                        contentDescription = destination.description
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}

@Composable
fun destinationsFactory(): List<DestinationInterface> {
    val diary = object : DestinationInterface {
        override val key: NavKey = DiaryScreen
        override val icon = painterResource(R.drawable.outline_event_available_24)
        override val description: String = stringResource(R.string.diary)
        override val label: String = stringResource(R.string.diary)
    }

    val coach = object : DestinationInterface {
        override val key: NavKey = CoachScreen
        override val icon = painterResource(R.drawable.outline_account_circle_24)
        override val description: String = stringResource(R.string.coach)
        override val label: String = stringResource(R.string.coach)
    }

    val pet = object : DestinationInterface {
        override val key: NavKey = PetScreen
        override val icon = painterResource(R.drawable.mood)
        override val description: String = stringResource(R.string.pet)
        override val label: String = stringResource(R.string.pet)
    }

    return listOf<DestinationInterface>(diary, coach, pet)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopBar(navigationManager: NavigationManager){
    val currentDest = navigationManager.currentDestination()
    val title = if (currentDest is LessonScreen){
        currentDest.lesson.subject
    } else {
        when (currentDest) {
            DiaryScreen -> stringResource(R.string.diary)
            CoachScreen -> stringResource(R.string.coach)
            PetScreen -> stringResource(R.string.pet)
            else -> stringResource(R.string.dnevnix)
        }
    }
    TopAppBar(title = {Text(title)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        actions = {
            if (currentDest !in listOf(DiaryScreen, CoachScreen, PetScreen)) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = stringResource(R.string.settings)
                    )
                }
            }
        },
    navigationIcon = {
        if (currentDest !in listOf(DiaryScreen, CoachScreen, PetScreen)) {
            IconButton(onClick = { navigationManager.back() }) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_back_24),
                    contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
    )
}
