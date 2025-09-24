package ru.annakondr.dnevnix.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import ru.annakondr.dnevnix.R
import androidx.core.net.toUri


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
fun BasicTopBar(title: String){
    TopAppBar(title = {Text(title)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        })
}
